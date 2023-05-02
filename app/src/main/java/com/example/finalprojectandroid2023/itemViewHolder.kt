package com.example.finalprojectandroid2023

import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class itemViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItemShop(item: Item){
        currentItem = item

        binding.itemName.text = currentItem.itemName
        binding.quantityText.text = currentItem.quantity.toString()

    }
}