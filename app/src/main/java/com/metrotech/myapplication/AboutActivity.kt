package com.metrotech.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metrotech.myapplication.databinding.ActivityAboutBinding


class AboutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }
    }
}