package com.smartsoft.foodymvvm.data

import com.smartsoft.foodymvvm.data.network.FoodRecipesApi
import com.smartsoft.foodymvvm.model.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private  val foodRecipesApi: FoodRecipesApi
){
    suspend fun getRecipes(queries : Map<String, String>): Response<FoodRecipe>{
        return foodRecipesApi.getRecipes(queries)
    }
}