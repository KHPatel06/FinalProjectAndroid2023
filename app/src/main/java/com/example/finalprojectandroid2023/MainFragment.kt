package com.example.finalprojectandroid2023

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.finalprojectandroid2023.databinding.FragmentMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.math.roundToInt

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        setHasOptionsMenu(true)

        dbRef = Firebase.database.reference

        binding.shopButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToShopFragment()
            rootView.findNavController().navigate(action)
        }

        binding.clickyThing.setOnClickListener { viewModel.addKush() }

        var currentTime = Calendar.getInstance().time
        binding.saveButton.setOnClickListener{
            val totalMultiplication = viewModel.totalMultiplication.value ?: 0.0
            val kushCount = viewModel.numOfKush.value ?: 0.0
            val itemList = viewModel.items

            val dbEntry = dbEntry(totalMultiplication, kushCount, itemList)

            dbRef.child("$currentTime").push().setValue(dbEntry)
            currentTime = Calendar.getInstance().time
        }



        viewModel.numOfKush.observe(viewLifecycleOwner) { currentKushAmount ->
            binding.cashCount.text = "$%.2f".format(currentKushAmount)
        }

        viewModel.totalMultiplication.observe(viewLifecycleOwner) { currentMult ->
            binding.totalMultiplier.text = "${(currentMult * 100).roundToInt()}%"
        }


        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allDBEntries = snapshot.children
                var dbIndex = 0
                for(saveDataEntries in allDBEntries){
                    for(singleSaveDataItem in saveDataEntries.children){

                        val kushCount = singleSaveDataItem.child("kushCount").value.toString().toDouble()
                        val multiplication = singleSaveDataItem.child("totalMultiplication").value.toString().toDouble()
                        val dbCount = allDBEntries.count()

                        if(dbIndex == dbCount){
                            for(i in 0..11) {
                                val itemQuantity = singleSaveDataItem.child("itemList").child("$i").child("quantity").child("value").value.toString().toInt()
                                val itemPrice = singleSaveDataItem.child("itemList").child("$i").child("price").child("value").value.toString().toDouble()
                                viewModel.updateQAndPAfterSave(itemQuantity, itemPrice, i)
                            }
                            viewModel.updateMultAndCountAfterSave(multiplication, kushCount)
                            dbIndex++
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }
        })

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}
class dbEntry(var totalMultiplication: Double = 0.0, var kushCount: Double = 0.0, var itemList: List<Item> = listOf())