package com.dviss.deliverytest.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dviss.deliverytest.R
import com.dviss.deliverytest.databinding.FragmentCartBinding
import com.dviss.deliverytest.ui.cart.recycler.CartAdapter

class CartFragment : Fragment(), CartAdapter.OnItemClickListener {

    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //======================== top app bar ==============================
        val locationTextView: TextView = binding.locationTextView
        val dateTextView: TextView = binding.dateTextView
        cartViewModel.state.observe(viewLifecycleOwner) {
            locationTextView.text = it.location
            dateTextView.text = it.date
        }

        val locationLayout: LinearLayout = binding.locationLayout
        locationLayout.setOnClickListener {
            cartViewModel.updateLocation()
        }

        //======================== recycler view ==============================
        cartAdapter = CartAdapter(this)
        val recyclerView: RecyclerView = binding.cartItemRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cartAdapter

        cartViewModel.state.observe(viewLifecycleOwner) {
            cartAdapter.setItems(it.cartUiItems)
        }

        //======================== button ==============================
        val button: TextView = binding.cartPayButton
        cartViewModel.state.observe(viewLifecycleOwner) {
            button.text = getString(R.string.pay, cartViewModel.calculateTotalPrice())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPlusClick(item: CartItemUiModel) {
        cartViewModel.plusItem(item)
    }

    override fun onMinusClick(item: CartItemUiModel) {
        cartViewModel.minusItem(item)
    }
}