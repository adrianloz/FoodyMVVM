package com.smartsoft.foodymvvm.data

import com.smartsoft.foodymvvm.data.database.RecipesDao
import com.smartsoft.foodymvvm.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourse @Inject constructor(private  val recipesDao : RecipesDao){

    fun readDatabase() : Flow<List<RecipesEntity>>{
        return  recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }

}