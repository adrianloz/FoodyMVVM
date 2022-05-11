package com.smartsoft.foodymvvm.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

    /**Esta funcion lo que hace es que hara que solo se observen los datos del livedata solo una vez
     * y no lo este haciendo siempre*/
    fun <T>LiveData<T>.observeOne(lifecycleOwner: LifecycleOwner, observer: Observer<T>){
        observe(lifecycleOwner, object  : Observer<T>{
            override fun onChanged(t: T) {
               removeObserver(this)
               observer.onChanged(t)
            }
        })

    }