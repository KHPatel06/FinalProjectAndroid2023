package com.example.finalprojectandroid2023

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.FragmentShopBinding
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;



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



        return rootView
    }

}