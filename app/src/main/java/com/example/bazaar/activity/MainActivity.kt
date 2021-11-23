package com.example.bazaar.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bazaar.R
import com.example.bazaar.fragment.MyFaresFragment
import com.example.bazaar.fragment.MyMarketFragment
import com.example.bazaar.fragment.TimelineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, TimelineFragment())
            .commit()

        bottomNavigationCallback()
    }

    private fun bottomNavigationCallback() {
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.timeline -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, TimelineFragment()).addToBackStack(null)
                        .commit()
                    true
                }
                R.id.my_market -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, MyMarketFragment()).addToBackStack(null)
                        .commit()
                    true
                }
                R.id.my_fares -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, MyFaresFragment()).addToBackStack(null)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}