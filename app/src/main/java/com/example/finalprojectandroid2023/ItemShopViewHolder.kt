package com.example.finalprojectandroid2023


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class ItemShopViewHolder(val binding: ListItemLayoutBinding, val viewModel: ItemViewModel, val lifecycleOwner: LifecycleOwner, val itemsPosition: Int) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItem(item: Item) {
        currentItem = item

        binding.itemName.text = currentItem.itemName
        viewModel.quantity.observe(lifecycleOwner){ currentQuantity ->

            viewModel.items[itemsPosition].quantity = currentQuantity
            binding.quantityText.text = "x${currentItem.quantity}"
        }
        binding.multiplicationAmount.text = "${(currentItem.kushMultiplier * 100).toInt()}% increase"
        binding.description.text = currentItem.desc
        viewModel.setPriceData(itemsPosition)
        viewModel.price.observe(lifecycleOwner){ currentPrice ->
            viewModel.items[itemsPosition].price = currentPrice
            binding.price.text =  "$%.2f".format(currentItem.price)
        }
    }

}

