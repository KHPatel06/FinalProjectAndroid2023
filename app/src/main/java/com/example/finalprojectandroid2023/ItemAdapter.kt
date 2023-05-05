package com.example.finalprojectandroid2023

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid2023.databinding.ListItemLayoutBinding

class ItemAdapter(val itemList: List<Item>) : RecyclerView.Adapter<ItemShopViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemShopViewHolder {
        val binding = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemShopViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bindItem(currentItem)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}