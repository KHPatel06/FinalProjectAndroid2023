package com.example.finalprojectandroid2023

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalprojectandroid2023.databinding.FragmentShopBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database


class MainFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val rootView = binding.root

        dbRef = Firebase.database.reference


        return rootView
    }

}