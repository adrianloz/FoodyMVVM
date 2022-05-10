package com.smartsoft.foodymvvm.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smartsoft.foodymvvm.model.FoodRecipe
import com.smartsoft.foodymvvm.utils.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipe: FoodRecipe) {
    @PrimaryKey
    var id: Int = 0
}