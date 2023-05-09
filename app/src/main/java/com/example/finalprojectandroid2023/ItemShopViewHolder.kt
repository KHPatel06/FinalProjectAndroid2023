package com.example.finalprojectandroid2023

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class ItemShopViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    private lateinit var currentItem: Item
    var currentMultiplier = 0.0

    fun bindItem(item: Item){
        currentItem = item

        binding.itemName.text = currentItem.itemName
        binding.quantityText.text = currentItem.quantity.toString()
        binding.multiplicationAmount.text = "${(currentItem.kushMultiplier*100).toInt()}%"
        binding.description.text = currentItem.desc
    }

    fun ItemViewHolder(convertView: View){

        convertView.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

    }

}