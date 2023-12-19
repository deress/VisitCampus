package com.dicoding.visitcampus.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.visitcampus.data.model.CarouselPhoto
import com.dicoding.visitcampus.databinding.ItemCarouselHomeBinding
import com.dicoding.visitcampus.ui.home.CarouselHomeAdapter.CarouselViewHolder

class CarouselHomeAdapter : ListAdapter<CarouselPhoto, CarouselViewHolder>(DIFF_CALLBACK) {
    inner class CarouselViewHolder(private val binding: ItemCarouselHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CarouselPhoto){
            binding.apply {
                val context = itemView.context
                val carouselResId = context.resources.getIdentifier(data.photo, "drawable", context.packageName)

                ivCarouselHome.setImageResource(carouselResId)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemCarouselHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val carousel = getItem(position)
        if (carousel!= null) {
            holder.bind(carousel)
        }

    }

    override fun getItemCount(): Int {
        return 2
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CarouselPhoto>() {
            override fun areItemsTheSame(oldItem: CarouselPhoto, newItem: CarouselPhoto): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: CarouselPhoto, newItem: CarouselPhoto): Boolean =
                oldItem.id == newItem.id
        }
    }



}