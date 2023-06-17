package com.dviss.deliverytest.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dviss.deliverytest.R
import com.dviss.deliverytest.databinding.FragmentCategoryMenuBinding
import com.dviss.deliverytest.domain.model.Dish
import com.dviss.deliverytest.ui.category.recycler.CategoryMenuAdapter
import com.dviss.deliverytest.ui.category.recycler.GridSpacingItemDecoration
import com.dviss.deliverytest.ui.category.dishdialog.DishDialogFragment

class CategoryMenu : Fragment(), CategoryMenuAdapter.OnItemClickListener {

    private var _binding: FragmentCategoryMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val categoryMenuViewModel: CategoryMenuViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

    private lateinit var categoryMenuAdapter: CategoryMenuAdapter

    private val args: CategoryMenuArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val upButton: ImageButton = binding.backButton
        upButton.setOnClickListener {
            val action = CategoryMenuDirections.actionNavigationCategoryMenuToNavigationHome()
            findNavController().navigate(action)
        }

        categoryMenuViewModel.setCategoryTitle(args.category)

        val categoryTextView: TextView = binding.categoryTextView
        categoryMenuViewModel.state.observe(viewLifecycleOwner) {
            categoryTextView.text = it.categoryTitle
        }

        categoryMenuAdapter = CategoryMenuAdapter(this)
        val recyclerView: RecyclerView = binding.categoryMenuRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        val spacingHorizontal =
            (8 * (context?.resources?.displayMetrics?.density?.toDouble() ?: 1.0)).toInt()
        val spacingVertical =
            (14 * (context?.resources?.displayMetrics?.density?.toDouble() ?: 1.0)).toInt()
        recyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                spacingHorizontal,
                spacingVertical
            )
        )
        recyclerView.adapter = categoryMenuAdapter

        // Set the categories list to recyclerview adapter
        categoryMenuViewModel.state.observe(viewLifecycleOwner) { state ->
            categoryMenuAdapter.setItems(state.dishes)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDishClick(dish: Dish) {
        val dialogFragment = DishDialogFragment(dish)
        dialogFragment.show(parentFragmentManager, "DishDialogFragment")
    }

}