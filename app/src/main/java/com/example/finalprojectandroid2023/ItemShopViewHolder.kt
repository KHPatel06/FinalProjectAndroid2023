package com.example.finalprojectandroid2023


 import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class ItemShopViewHolder(val binding: ListItemLayoutBinding, val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentItem: Item

    fun bindItem(item: Item) {
        currentItem = item

        binding.itemName.text = currentItem.itemName
        currentItem.quantity.observe(lifecycleOwner){ currentQuantity ->
            binding.quantityText.text = "x${currentItem.quantity.value}"
        }
        binding.multiplicationAmount.text = "${(currentItem.kushMultiplier * 100).toInt()}% increase"
        binding.description.text = currentItem.desc
        binding.price.text =  "$%.2f".format(currentItem.price)
    }

}

