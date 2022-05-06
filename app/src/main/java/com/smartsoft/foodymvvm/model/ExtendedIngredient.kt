package com.smartsoft.foodymvvm.model

import java.io.Serializable

data class ExtendedIngredient(
    val amount : Double,
    val consistency : String,
    val image : String,
    val name : String,
    val original : String,
    val unit : String
):Serializable