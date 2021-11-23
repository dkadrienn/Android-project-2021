package com.example.bazaar.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bazaar.R
import com.example.bazaar.fragment.SplashFragment

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        supportFragmentManager.beginTransaction().replace(R.id.logFragment, SplashFragment())
            .commit()
    }
}