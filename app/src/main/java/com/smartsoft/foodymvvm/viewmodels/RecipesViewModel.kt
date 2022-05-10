package com.smartsoft.foodymvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.smartsoft.foodymvvm.utils.Constants
import com.smartsoft.foodymvvm.utils.Constants.Companion.API_KEY
import com.smartsoft.foodymvvm.utils.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.smartsoft.foodymvvm.utils.Constants.Companion.QUERY_API_KEY
import com.smartsoft.foodymvvm.utils.Constants.Companion.QUERY_DIET
import com.smartsoft.foodymvvm.utils.Constants.Companion.QUERY_FILLINGREDIENTS
import com.smartsoft.foodymvvm.utils.Constants.Companion.QUERY_NUMBER
import com.smartsoft.foodymvvm.utils.Constants.Companion.QUERY_TYPE

class RecipesViewModel(application: Application): AndroidViewModel(application) {


    fun applyQueries(): HashMap<String, String>{
        val queries : HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = "50" //numero de recetas que se pueden optener
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILLINGREDIENTS] = "true"

        return  queries
    }
}