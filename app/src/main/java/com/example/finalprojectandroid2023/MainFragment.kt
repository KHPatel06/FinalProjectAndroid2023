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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import kotlin.math.roundToInt
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor


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
            viewModel.addKush(viewModel.totalMultiplication.value!!)
        }
        viewModel.numOfKush.observe(viewLifecycleOwner) { currentKushAmount ->
            binding.cashCount.text = "$%.2f".format(currentKushAmount)
        }
        viewModel.totalMultiplication.observe(viewLifecycleOwner){ currentMult ->
            binding.totalMultiplier.text = "${(currentMult * 100).roundToInt()}%"
        }


        return rootView
    }

}