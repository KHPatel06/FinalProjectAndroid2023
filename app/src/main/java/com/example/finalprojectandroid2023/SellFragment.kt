package com.example.finalprojectandroid2023

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.FragmentShopBinding


class SellFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val adapter = ItemSellAdapter(viewModel.items)
        binding.recyclerView.adapter = adapter

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {

                    }
                    override fun onLongItemClick(view: View?, position: Int) {}
                })
        )

        return rootView
    }

}