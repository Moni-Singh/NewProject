package com.example.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.viewpager.widget.ViewPager
import com.example.newproject.Adapter.ImagePagerAdapter
import java.util.*

class Dashboard : AppCompatActivity() {


        private lateinit var viewPager: ViewPager
        private lateinit var adapter: ImagePagerAdapter
        private val images = listOf(R.drawable.logo, R.drawable.ic_baseline_lock_open_24, R.drawable.home)
        private var currentPage = 0
        private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        viewPager = findViewById<ViewPager>(R.id.viewPager)
            adapter = ImagePagerAdapter(images, this)
            viewPager.adapter = adapter

            val handler = Handler()
            val update = Runnable {
                if (currentPage == images.size) {
                    currentPage = 0
                }
                viewPager.setCurrentItem(currentPage++, true)
            }

            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    handler.post(update)
                }
            }, 2000, 2000) // Change image every 2 seconds
        }

        override fun onDestroy() {
            super.onDestroy()
            timer?.cancel()
        }
    }
