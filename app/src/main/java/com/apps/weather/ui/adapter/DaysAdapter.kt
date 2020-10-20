package com.apps.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.weather.data.model.LocationResult
import com.apps.weather.databinding.ItemDaysBinding

class DaysAdapter : RecyclerView.Adapter<DaysAdapter.ItemViewHolder>() {

    private val list = arrayListOf<LocationResult>()

    fun setList(list: List<LocationResult>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder = ItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ItemViewHolder(private val binding: ItemDaysBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocationResult) {
            binding.viewState = item
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val binding = ItemDaysBinding.inflate(LayoutInflater.from(parent.context))
                return ItemViewHolder(binding)
            }
        }
    }

}