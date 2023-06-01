package com.example.finalprojectandroid2023

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutSellBinding

class ItemSellViewHolder(val binding: ListItemLayoutSellBinding, val viewModel: ItemViewModel, val lifecycleOwner: LifecycleOwner, val itemsPosition: Int): RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItem(item: Item) {
        currentItem = item
        binding.itemNameSell.text = currentItem.itemName
        viewModel.quantity.observe(lifecycleOwner) { currentQuantity ->
            viewModel.items[itemsPosition].quantity = currentQuantity
            binding.quantityTextSell.text = "x${currentItem.quantity}"
        }
    }
}