package com.dviss.deliverytest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dviss.deliverytest.R
import com.dviss.deliverytest.databinding.FragmentHomeBinding
import com.dviss.deliverytest.domain.model.Category
import com.dviss.deliverytest.ui.home.recycler.CategoryAdapter

private const val TAG = "HomeFragment"

class HomeFragment(

) : Fragment(), CategoryAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val locationTextView: TextView = binding.locationTextView
        val dateTextView: TextView = binding.dateTextView
        homeViewModel.state.observe(viewLifecycleOwner) {
            locationTextView.text = it.location
            dateTextView.text = it.date
        }

        val locationLayout: LinearLayout = binding.locationLayout
        locationLayout.setOnClickListener {
            homeViewModel.updateLocation()
        }

        // Initialize RecyclerView and Adapter
        categoryAdapter = CategoryAdapter(this)
        val recyclerView: RecyclerView = binding.categoryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = categoryAdapter

        // Set the categories list to recyclerview adapter
        homeViewModel.state.observe(viewLifecycleOwner) { state ->
            categoryAdapter.setItems(state.categories)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateLocation() {
        homeViewModel.updateLocation()
    }

    override fun onCategoryItemClick(category: Category) {
        Log.d(TAG, "onItemClick: ${category.name}")
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationCategoryMenu(category.name)
        findNavController().navigate(action)
    }
}