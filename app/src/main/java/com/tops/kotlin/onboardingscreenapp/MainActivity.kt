package com.tops.kotlin.onboardingscreenapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import androidx.viewpager.widget.ViewPager.GONE
import androidx.viewpager.widget.ViewPager.LayoutParams
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.tops.kotlin.onboardingscreenapp.databinding.ActivityMainBinding
import com.tops.kotlin.onboardingscreenapp.models.Item

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var itemList = mutableListOf<Item>()

    private lateinit var itemAdapter: ItemListAdapter

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //add item to list
        itemList.add(Item(R.drawable.illustration1, "Search", "Lorem ipsum dolor sit amet. Sed maxime nihil est voluptas quos ut architecto tenetur."))
        itemList.add(Item(R.drawable.illustration2, "Order", "Lorem ipsum dolor sit amet. Sed maxime nihil est voluptas quos ut architecto tenetur."))
        itemList.add(Item(R.drawable.illustration3, "Delivery", "Lorem ipsum dolor sit amet. Sed maxime nihil est voluptas quos ut architecto tenetur."))

        itemAdapter = ItemListAdapter(this, itemList)
        binding.viewPager.adapter = itemAdapter

        binding.viewPager.currentItem = currentIndex

        updateIndicator(currentIndex)

        binding.btnPrevious.visibility = View.INVISIBLE

        binding.btnPrevious.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                binding.viewPager.currentItem = currentIndex
            }
        }

        binding.btnNext.setOnClickListener {
            if (currentIndex < itemList.size - 1) {
                currentIndex++
                binding.viewPager.currentItem = currentIndex
            } else {
                // Navigate to the login/register activity
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show()
            }
        }

        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) { }

            override fun onPageSelected(position: Int) {
                currentIndex = position

                if (currentIndex == 0) {
                    binding.btnPrevious.visibility = View.INVISIBLE
                } else {
                    binding.btnPrevious.visibility = View.VISIBLE
                }

                if (currentIndex < itemAdapter.count - 1) {
                    binding.btnNext.text = "Next"
                } else {
                    binding.btnNext.text = "Finish"
                }

                updateIndicator(currentIndex)
            }

            override fun onPageScrollStateChanged(state: Int) { }

        })
    }

    private fun updateIndicator(index: Int) {

        binding.layoutIndicator.removeAllViews()

        for (i in 0 until itemAdapter.count) {
            var imageView = ImageView(this)

            if (index == i) {
                // active
                imageView.setImageResource(R.drawable.active_indicator)
            } else {
                // inactive
                imageView.setImageResource(R.drawable.inactive_indicator)
            }
            var params = LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 12, 0)

            binding.layoutIndicator.addView(imageView, params)
        }

    }
}