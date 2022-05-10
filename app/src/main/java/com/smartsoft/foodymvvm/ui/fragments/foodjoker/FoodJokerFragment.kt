package com.smartsoft.foodymvvm.ui.fragments.foodjoker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartsoft.foodymvvm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodJokerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_joker, container, false)
    }
}