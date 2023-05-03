package com.example.finalprojectandroid2023

import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class ItemViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItem(item: Item){
        currentItem = item

        binding.itemName.text = currentItem.itemName
        binding.quantityText.text = currentItem.quantity.toString()
        binding.multiplicationAmount.text = "${(currentItem.kushMultiplier*100).toInt()}%"
        binding.description.text = currentItem.desc
    }

    init {

    }

}