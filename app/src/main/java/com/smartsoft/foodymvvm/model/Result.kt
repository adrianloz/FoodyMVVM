package com.smartsoft.foodymvvm.model

import java.io.Serializable

data class Result(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val creditsText: String,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val extendedIngredient: List<ExtendedIngredient>,
    val glutenFree: Boolean,
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean
):Serializable