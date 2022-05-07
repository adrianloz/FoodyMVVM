package com.smartsoft.foodymvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smartsoft.foodymvvm.databinding.CardRecetasLayoutBinding
import com.smartsoft.foodymvvm.model.FoodRecipe
import com.smartsoft.foodymvvm.model.Result
import com.smartsoft.foodymvvm.utils.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {
    private var recipes = emptyList<Result>()
    class MyViewHolder(private val binding: CardRecetasLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(result: Result){
            binding.result
            binding.executePendingBindings()//actualiza el diseño cada vez que haya un cambio en los datos

        }

        companion object{
            fun from(parent: ViewGroup) : MyViewHolder{
                val layoutInflate = LayoutInflater.from(parent.context)
                val binding = CardRecetasLayoutBinding.inflate(layoutInflate, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData : FoodRecipe){
        val recipesDiffUtil = RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}