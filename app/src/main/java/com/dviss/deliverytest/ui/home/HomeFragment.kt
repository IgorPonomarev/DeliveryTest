package com.dviss.deliverytest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.ViewModelProvider
import com.dviss.deliverytest.MainActivity
import com.dviss.deliverytest.R
import com.dviss.deliverytest.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class HomeFragment(

) : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateLocation() {
        homeViewModel.updateLocation()
    }
}