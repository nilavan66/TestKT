package com.example.testkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.testkt.icon.Icon
import com.example.testkt.icon.IconAdapter
import com.example.testkt.image.ImageItem
import com.example.testkt.image.ImageSliderAdapter
import com.google.android.material.card.MaterialCardView
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlin.math.abs

class MyJio : AppCompatActivity(), IconAdapter.ViewMoreClickListener {
    private lateinit var viewPager2: ViewPager2
    private lateinit var iconRecycle: RecyclerView

    private lateinit var iconArrayList: ArrayList<Icon>
    private lateinit var iconAdapter: IconAdapter

    private lateinit var less: MaterialCardView

    private lateinit var dotsIndicator: WormDotsIndicator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_jio)


        viewPager2 = findViewById(R.id.viewPager)
        iconRecycle = findViewById(R.id.iconRecycle)

        less = findViewById(R.id.less)

        dotsIndicator = findViewById(R.id.worm_dot)

        iconRecycle.layoutManager = GridLayoutManager(this, 4)

        iconArrayList = ArrayList()
        iconArrayList.add(Icon(R.drawable.arrow, "Mobile"))
        iconArrayList.add(Icon(R.drawable.mobile, "Fiber"))
        iconArrayList.add(Icon(R.drawable.chat, "Music"))
        iconArrayList.add(Icon(R.drawable.location, "Jio TV"))
        iconArrayList.add(Icon(R.drawable.shield, "Shorts"))
        iconArrayList.add(Icon(R.drawable.wifi, "Shop"))
        iconArrayList.add(Icon(R.drawable.arrow, "eLearning"))
        iconArrayList.add(Icon(R.drawable.mobile, "UPI"))
        iconArrayList.add(Icon(R.drawable.chat, "Play&Win"))
        iconArrayList.add(Icon(R.drawable.location, "Games"))
        iconArrayList.add(Icon(R.drawable.shield, "News"))
        iconArrayList.add(Icon(R.drawable.wifi, "Jobs"))
        iconArrayList.add(Icon(R.drawable.arrow, "Pharmacy"))
        iconArrayList.add(Icon(R.drawable.mobile, "Bank"))
        iconArrayList.add(Icon(R.drawable.chat, "Health"))
        iconArrayList.add(Icon(R.drawable.location, "Backup"))
        iconArrayList.add(Icon(R.drawable.shield, "Movies"))
        iconArrayList.add(Icon(R.drawable.wifi, "Events"))
        iconArrayList.add(Icon(R.drawable.mobile, "Jio Store"))


        iconAdapter = IconAdapter(iconArrayList, this)
        iconRecycle.adapter = iconAdapter

        less.setOnClickListener {
            onViewLessClick()
        }


        val imageList = listOf(
            ImageItem(R.drawable._1),
            ImageItem(R.drawable._2),
            ImageItem(R.drawable._3),
            ImageItem(R.drawable._1),
            ImageItem(R.drawable._2),
            ImageItem(R.drawable._3),


            )

        val adapter = ImageSliderAdapter(imageList)
        viewPager2.adapter = adapter

        dotsIndicator.setViewPager2(viewPager2)

        viewPager2.offscreenPageLimit=2
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{page, position->
            val offset = resources.getDimensionPixelOffset(R.dimen.view_pager_offset)
            //val margin = resources.getDimensionPixelOffset(R.dimen.view_pager_margin)

            val scaleFactor = 0.85f + (1 - abs(position)) * 0.15f

            page.translationX = -offset * position
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor

            // Set a lower elevation for previous and next images to prevent overlapping
            if (position != 0.0f) {
                page.elevation = 0.0f
            } else {
                // Set a higher elevation for the current image to bring it to the front
                page.elevation = 1.0f
            }

            // Apply margin for the current image
            if (position == 0.0f) {
                page.setPadding(40, 0, 40, 0)
            } else {
                page.setPadding(40, 0, 40, 0)
            }

        }
        viewPager2.setPageTransformer(transformer)
    }

    override fun onViewMoreClick() {
        iconAdapter.setShowAllItems(true)
        less.visibility = View.VISIBLE
    }

    private fun onViewLessClick() {
        iconAdapter.setShowAllItems(false)
        less.visibility = View.GONE
    }

}