package com.ciconia.android.ciconiabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.ciconia.android.ciconiabase.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.presenter = this
    }

    fun onButtonPressed() {
        binding.loadingButton.onStartLoading()
        Handler().postDelayed({
            binding.loadingButton.onStopLoading()
        }, 500)
    }
}
