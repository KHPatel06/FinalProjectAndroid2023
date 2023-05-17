package com.example.finalprojectandroid2023

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class ItemShopViewHolder(val binding: ListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItem(item: Item) {
        currentItem = item

        binding.itemName.text = currentItem.itemName
        binding.quantityText.text = "x${currentItem.quantity}"
        binding.multiplicationAmount.text = "${(currentItem.kushMultiplier * 100).toInt()}% increase"
        binding.description.text = currentItem.desc
        binding.price.text =  "$%.2f".format(currentItem.price)
    }

}