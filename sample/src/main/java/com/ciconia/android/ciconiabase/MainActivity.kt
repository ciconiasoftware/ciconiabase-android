package com.ciconia.android.ciconiabase

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.ciconia.android.ciconiabase.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var accentColor: ObservableField<Int> = ObservableField()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.presenter = this
        accentColor.set(Color.BLACK)
    }

    fun onButtonPressed() {
        Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
        binding.loadingButton1.onStartLoading()
        Handler().postDelayed({
            binding.loadingButton1.onStopLoading()
        }, 500)
    }

//    fun onButton2Pressed() {
//        Toast.makeText(this,"2",Toast.LENGTH_SHORT).show()
//        binding.loadingButton2.onStartLoading()
//        Handler().postDelayed({
//            binding.loadingButton2.onStopLoading()
//        }, 500)
//    }
}
