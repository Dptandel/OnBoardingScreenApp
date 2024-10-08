package com.tops.kotlin.onboardingscreenapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.tops.kotlin.onboardingscreenapp.databinding.ItemLayoutBinding
import com.tops.kotlin.onboardingscreenapp.models.Item

class ItemListAdapter(var context: Context, var itemList: MutableList<Item>) : PagerAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var binding = ItemLayoutBinding.inflate(LayoutInflater.from(context), container, false)

        var item = itemList[position]

        binding.ivThumbnail.setImageResource(item.image)
        binding.tvTitle.text = item.title
        binding.tvDescription.text = item.description

        // add item view to container
        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }
}