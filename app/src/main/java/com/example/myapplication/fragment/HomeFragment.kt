package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.myapplication.R
import com.example.myapplication.adapter.SliderAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.SliderModel
import com.example.myapplication.viewModel.MainViewModel

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBanner()
    }

    private fun banners(image: List<SliderModel>){
        binding.viewPager2.adapter = SliderAdapter(image, binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Khoảng cách giữa các trang
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewPager2.setPageTransformer(compositePageTransformer)
        if (image.size > 1){
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPager2)
        }
    }
    private fun initBanner(){
        binding.progressBarSlider.visibility = View.VISIBLE

        // Khi "banners" thay đổi thì thực hiện lệnh trong Observer
        // "it" là "banners"
        viewModel.banners.observe(viewLifecycleOwner, Observer {
            banners(it)
            binding.progressBarSlider.visibility = View.GONE
        })

        viewModel.loadBanners()
    }


}