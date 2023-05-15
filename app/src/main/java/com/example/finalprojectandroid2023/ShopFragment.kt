package com.example.finalprojectandroid2023

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.FragmentShopBinding


class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val adapter = ItemAdapter(viewModel.items)
        binding.recyclerView.adapter = adapter

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        if(viewModel.numOfKush.value!! >= viewModel.items[position].price){
                            viewModel.buyItem(position)
                        }
                    }
                    override fun onLongItemClick(view: View?, position: Int) {}
                })
        )

        return rootView
    }
}