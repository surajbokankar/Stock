package com.example.stockapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.stockapp.MainActivity
import com.example.stockapp.R

class SplashActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_splash_view)
        initView()
    }

    private fun initView() {
        Handler().postDelayed({
          startActivity(Intent(this,MainActivity::class.java))
            finish()
        },700)
    }
}