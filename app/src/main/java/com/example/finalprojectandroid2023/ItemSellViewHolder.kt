package com.example.finalprojectandroid2023

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutSellBinding

class ItemSellViewHolder(val binding: ListItemLayoutSellBinding, val lifecycleOwner: LifecycleOwner): RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItem(item: Item) {
        currentItem = item
        binding.itemNameSell.text = currentItem.itemName
        currentItem.quantity.observe(lifecycleOwner){
            binding.quantityTextSell.text = "x${currentItem.quantity.value}"
        }
    }
}