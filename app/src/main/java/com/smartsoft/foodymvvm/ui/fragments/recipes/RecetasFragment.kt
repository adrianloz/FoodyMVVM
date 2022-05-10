package com.smartsoft.foodymvvm.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartsoft.foodymvvm.viewmodels.MainViewModel
import com.smartsoft.foodymvvm.R
import com.smartsoft.foodymvvm.adapters.RecipesAdapter
import com.smartsoft.foodymvvm.databinding.FragmentRecetasBinding
import com.smartsoft.foodymvvm.utils.Constants
import com.smartsoft.foodymvvm.utils.NetworkResult
import com.smartsoft.foodymvvm.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecetasFragment : Fragment() {
    private lateinit var recipesViewModel : RecipesViewModel
    private lateinit var mainViewModel: MainViewModel
    lateinit var binding: FragmentRecetasBinding
    private val mAdapter by lazy {RecipesAdapter()}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recetas, container, false)
        binding = FragmentRecetasBinding.bind(view)

        setupRecyclerView()
        requestApiData()
        return binding.root
    }

    private fun requestApiData(){
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let {
                        mAdapter.setData(it)
                    }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun setupRecyclerView(){
        binding.recyclerViewRecetas.adapter = mAdapter
        binding.recyclerViewRecetas.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }
    private fun showShimmerEffect() {
        binding.recyclerViewRecetas.showShimmer()
    }

    private  fun hideShimmerEffect(){
        binding.recyclerViewRecetas.hideShimmer()
    }
}