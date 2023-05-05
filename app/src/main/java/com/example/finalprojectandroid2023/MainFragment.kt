package com.example.finalprojectandroid2023

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.finalprojectandroid2023.databinding.FragmentMainBinding
import com.example.finalprojectandroid2023.databinding.FragmentShopBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef : DatabaseReference
    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root

        dbRef = Firebase.database.reference

        binding.shopButton.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToShopFragment()
            rootView.findNavController().navigate(action)
        }

        binding.clickyThing.setOnClickListener {
            viewModel.addKush(.000000000001)
        }
        viewModel.numOfKush.observe(viewLifecycleOwner) { currentKushAmount ->
            binding.cashCount.text = currentKushAmount.toString()
        }

        return rootView
    }

}