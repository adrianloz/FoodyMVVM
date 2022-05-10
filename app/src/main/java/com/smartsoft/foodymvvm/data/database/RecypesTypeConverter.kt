package com.smartsoft.foodymvvm.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smartsoft.foodymvvm.model.FoodRecipe

class RecypesTypeConverter {
    var gson = Gson()
    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe) : String{
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoofRecipe(data : String) : FoodRecipe{
        val listType = object  : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }
}