package com.smartsoft.foodymvvm.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.smartsoft.foodymvvm.data.RepositoryRecipes
import com.smartsoft.foodymvvm.data.database.RecipesEntity
import com.smartsoft.foodymvvm.model.FoodRecipe
import com.smartsoft.foodymvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryRecipes: RepositoryRecipes,
    application : Application) : AndroidViewModel(application) {

    /** ROOM DATABASE*/
    val readRecipes : LiveData<List<RecipesEntity>> = repositoryRecipes.local.readDatabase().asLiveData()
    private  fun insertRecipes(recipesEntity: RecipesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repositoryRecipes.local.insertRecipes(recipesEntity)
        }
    /** RETROFIT */
    var recipesResponse : MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    fun getRecipes(queries : Map<String, String>) = viewModelScope.launch{
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
       if (hasInternetConnection()){
            try {
                val response = repositoryRecipes.remote.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)

                val foodRecipe = recipesResponse.value!!.data
                if (foodRecipe != null){
                    offlineCacheRecipes(foodRecipe)
                }
            }catch (e:Exception){
                recipesResponse.value = NetworkResult.Error("Recipes not found.")
            }
       }else{
           recipesResponse.value = NetworkResult.Error("No Internet Connection")
       }
    }

    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val recipesEntity = RecipesEntity(foodRecipe)
        insertRecipes(recipesEntity)
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when{
            response.message().toString().contains("timeout") ->{
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 ->{
                return  NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.results.isNullOrEmpty()->{
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful->{
                val foodRecipes = response.body()
                return  NetworkResult.Success(foodRecipes!!)
            }
            else->{
                return  NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection() : Boolean{
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        )as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}