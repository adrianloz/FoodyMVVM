package com.smartsoft.foodymvvm.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.smartsoft.foodymvvm.R

class RecipesRowBinding {
    companion object{
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl : String){
            imageView.load(imageUrl){
                crossfade(600)
                error(R.drawable.ic_baseline_hide_image_24)
                placeholder(R.drawable.ic_baseline_hide_image_24)
            }
        }

        @BindingAdapter("setNumberofLikes")
        @JvmStatic
        fun setNumberofLikes(textView: TextView, likes : Int){
            textView.text = likes.toString()
        }

       @BindingAdapter("setNumberOfMinutes")
       @JvmStatic
        fun setNumberOfMinutes(textView: TextView, minutes : Int){
            textView.text = minutes.toString()
        }
        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view : View, vegan : Boolean){
            if (vegan){
                when(view){
                    is TextView ->{
                        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                    }
                    is ImageView ->{
                        view.setColorFilter(ContextCompat.getColor(view.context,R.color.green))
                    }
                }
            }
        }

    }
}