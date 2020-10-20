package com.apps.weather.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.weather.R
import com.apps.weather.data.model.Location
import com.apps.weather.databinding.ItemCitiesBinding
import kotlinx.android.synthetic.main.item_cities.view.*

class CitiesAdapter : RecyclerView.Adapter<CitiesAdapter.ItemViewHolder>() {

    private val list = arrayListOf<Location>()

    private lateinit var listener: OnItemClickListener

    private var index = 0

    fun setList(list: List<Location>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener (listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder = ItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.container.setOnClickListener {
            index = position
            notifyDataSetChanged()
        }

        if(index==position){
            holder.iconSet()
            listener.onClickListener(list[position],position)
        }
    }

    class ItemViewHolder(private val binding: ItemCitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Location) {
            binding.viewState = item
            binding.root.container.setBackgroundResource(R.drawable.rectangle_common_menu_passive)
            binding.root.textView.setTextColor(Color.parseColor("#4c5264"))
            binding.executePendingBindings()
        }

        fun iconSet(){
            binding.root.container.setBackgroundResource(R.drawable.rectangle_common_menu_active)
            binding.root.textView.setTextColor(Color.parseColor("#ffffff"))
        }

        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val binding = ItemCitiesBinding.inflate(LayoutInflater.from(parent.context))
                return ItemViewHolder(binding)
            }
        }
    }

    interface OnItemClickListener {
        fun onClickListener(result: Location, position: Int)
    }

}