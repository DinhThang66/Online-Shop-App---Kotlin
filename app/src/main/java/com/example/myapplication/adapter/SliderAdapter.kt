package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.databinding.SliderItemContainerBinding
import com.example.myapplication.model.SliderModel


class SliderAdapter(
    private val sliderItems: List<SliderModel>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context = parent.context
        val binding = SliderItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position], context)

        if (position == sliderItems.lastIndex - 1){
            viewPager2.post { viewPager2.setCurrentItem(0, false) }
        }
    }

    override fun getItemCount(): Int = sliderItems.size


    class SliderViewHolder(private val binding: SliderItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setImage(sliderItem: SliderModel, context: Context){
            Glide.with(context)
                .load(sliderItem.url)
                .apply { RequestOptions().transform(CenterInside()) }
                .into(binding.imageSlide)
        }
    }
}