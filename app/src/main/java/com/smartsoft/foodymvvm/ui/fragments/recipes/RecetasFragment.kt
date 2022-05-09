package com.smartsoft.foodymvvm.ui.fragments.recipes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.smartsoft.foodymvvm.R
import com.smartsoft.foodymvvm.databinding.FragmentRecetasBinding

class RecetasFragment : Fragment() {
    lateinit var binding: FragmentRecetasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recetas, container, false)
        binding = FragmentRecetasBinding.bind(view)
        showData()
        return binding.root
    }

    private fun showData() {

        binding.recyclerViewRecetas.showShimmer()
    }

}