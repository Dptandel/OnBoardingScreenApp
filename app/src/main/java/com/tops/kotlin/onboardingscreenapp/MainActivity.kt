package com.tops.kotlin.onboardingscreenapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.kotlin.onboardingscreenapp.databinding.ActivityMainBinding
import com.tops.kotlin.onboardingscreenapp.models.Item

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var itemList = mutableListOf<Item>()

    private lateinit var itemAdapter: ItemListAdapter

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
    }
}