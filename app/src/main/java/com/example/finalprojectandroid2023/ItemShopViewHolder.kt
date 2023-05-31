package com.example.finalprojectandroid2023


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class ItemShopViewHolder(val binding: ListItemLayoutBinding, val viewModel: ItemViewModel) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    private lateinit var currentItem: Item
    private lateinit var lifecycleRegistry: LifecycleRegistry


    fun bindItem(item: Item) {
        currentItem = item

        binding.itemName.text = currentItem.itemName
        viewModel.quantity.observe({lifecycle}){ currentQuantity ->
            binding.quantityText.text = "x${currentQuantity}"
        }
        binding.multiplicationAmount.text = "${(currentItem.kushMultiplier * 100).toInt()}% increase"
        binding.description.text = currentItem.desc
        binding.price.text =  "$%.2f".format(currentItem.price)
    }

    override fun getLifecycle(): Lifecycle {
        TODO()
    }


}

