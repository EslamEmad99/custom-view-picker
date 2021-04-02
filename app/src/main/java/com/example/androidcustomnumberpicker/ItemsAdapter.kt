package com.example.androidcustomnumberpicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcustomnumberpicker.databinding.ItemBinding

class ItemsAdapter() : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    private var items: ArrayList<ItemModel> = ArrayList()

    fun swapData(items: ArrayList<ItemModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): ItemModel {
        return items[position]
    }

    fun changeColor(position: Int) {
        items.forEach {
            it.current = false
        }
        items[position].current = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.numberTv.text = "${item.id}"
        holder.binding.nameTv.text = item.name
        if (item.current) {
            holder.binding.numberTv.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.teal_200
                )
            )

            holder.binding.nameTv.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.purple_200
                )
            )

            holder.binding.card.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.purple_500
                )
            )
        }
    }

    override fun getItemCount() = items.size

    class MyViewHolder(var binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}