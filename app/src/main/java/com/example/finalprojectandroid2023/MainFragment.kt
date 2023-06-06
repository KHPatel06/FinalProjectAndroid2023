package com.example.finalprojectandroid2023

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.finalprojectandroid2023.databinding.FragmentMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.roundToInt

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: ItemViewModel by activityViewModels()
    var check = true

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

        binding.saveButton.setOnClickListener{
            val totalMultiplication = viewModel.totalMultiplication.value ?: 0.0
            val kushCount = viewModel.numOfKush.value ?: 0.0
            val itemList = viewModel.items

            val dbEntry = DBEntry(totalMultiplication, kushCount, itemList)

            dbRef.child("saveStatePath").removeValue()
            dbRef.child("saveStatePath").push().setValue(dbEntry)
        }

        binding.resetSaveButton.setOnClickListener{
            val items = listOf(
                Item(0.5, MutableLiveData(0), "Cookie", "Eating it makes you feel like clicking more Kush's", MutableLiveData(100.0)),
                Item(1.0, MutableLiveData(0), "Ghost Energy", "You feel energized, you should click more Kush's", MutableLiveData(1000.0)),
                Item(1.5, MutableLiveData(0), "Gaming chair", "Just makes you better, click away", MutableLiveData(1500.0)),
                Item(2.0, MutableLiveData(0), "Kush Farm", "Farms boost how many Kush's you make per click", MutableLiveData(2000.0)),
                Item(2.5, MutableLiveData(0), "Motorized Mouse", "Mechanically increases the amount of Kush's you get per click", MutableLiveData(10000.0)),
                Item(3.0, MutableLiveData(0), "Nathan", "I just felt like putting Nathan in here", MutableLiveData(15000.0)),
                Item(3.5, MutableLiveData(0), "Kush Express Bank", "Takes your Kush's and puts interest on them over time", MutableLiveData(18000.0)),
                Item(4.0, MutableLiveData(0), "Wizard", "He'll give you more Kush with his magic and whatnot", MutableLiveData(20000.0)),
                Item(4.5, MutableLiveData(0), "Kush Inc", "A corporate machine that massively increases your Kush income", MutableLiveData(25000.0)),
                Item(5.0, MutableLiveData(0), "Magic TI-84 plus ", "Does the math and gives you clicks once you press enter",MutableLiveData(28000.0)),
                Item(5.5, MutableLiveData(0), "Time Machine", "Go back in time and get Kush's before they were even born", MutableLiveData(31000.0)),
                Item(10.0, MutableLiveData(0), "Kush Empire", "Rule the world with the insane click power this gives you", MutableLiveData(1000000.0)),
            )

            val dbEntry = DBEntry(0.0, 0.0, items)

            dbRef.child("saveStatePath").removeValue()
            dbRef.child("saveStatePath").push().setValue(dbEntry)
        }

        val valueEventListener = object: ValueEventListener{
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

                        for(i in dbCount downTo dbIndex){

                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }

        }

        if(check) {
            dbRef.addValueEventListener(valueEventListener)
            check = false
        }
        else {
            dbRef.removeEventListener(valueEventListener)
        }

        viewModel.numOfKush.observe(viewLifecycleOwner) { currentKushAmount ->
            binding.cashCount.text = "$%.2f".format(currentKushAmount)
        }

        viewModel.totalMultiplication.observe(viewLifecycleOwner) { currentMult ->
            binding.totalMultiplier.text = "${(currentMult * 100).roundToInt()}%"
        }

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
class DBEntry(var totalMultiplication: Double = 0.0, var kushCount: Double = 0.0, var itemList: List<Item> = listOf())