package com.example.finalprojectandroid2023

import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutSellBinding

class ItemSellViewHolder(val binding: ListItemLayoutSellBinding): RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItem(item: Item) {
        currentItem = item

        binding.itemNameSell.text = currentItem.itemName
        binding.quantityTextSell.text = "x${currentItem.quantity}"

    }
}