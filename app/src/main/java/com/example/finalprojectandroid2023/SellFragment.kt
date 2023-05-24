package com.example.finalprojectandroid2023

import android.app.Dialog
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.FragmentShopBinding
import org.w3c.dom.Text
import java.util.*


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
                        val dialog = Dialog(context!!)
                        val inflater = layoutInflater
                        val layout = inflater.inflate(R.layout.dialog_sell, container, false)
                        dialog.setContentView(layout)
                        dialog.show()

                        layout.findViewById<SeekBar>(R.id.quantitySeekbar).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                            override fun onProgressChanged(
                                seekBar: SeekBar?,
                                progress: Int,
                                fromUser: Boolean
                            ) {
                                layout.findViewById<TextView>(R.id.quantity_to_sell).text = progress.toString()
                            }
                            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                        })
                    }
                    override fun onLongItemClick(view: View?, position: Int) {}
                })
        )

        return rootView
    }

}