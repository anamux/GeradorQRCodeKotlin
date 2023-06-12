package com.anamuxfeldt.appgeradorqrcodekotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anamuxfeldt.appgeradorqrcodekotlin.databinding.ActivityFirstBinding



class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnGerarQRCode!!.setOnClickListener {

            val intent = Intent (this@FirstActivity, MainActivity::class.java )
            startActivity(intent)

        }
    }

}