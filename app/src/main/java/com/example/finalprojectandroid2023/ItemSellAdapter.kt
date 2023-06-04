package com.example.finalprojectandroid2023

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutSellBinding

class ItemSellAdapter(val itemList: List<Item>, val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<ItemSellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSellViewHolder {
        val binding = ListItemLayoutSellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemSellViewHolder(binding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: ItemSellViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bindItem(currentItem)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}