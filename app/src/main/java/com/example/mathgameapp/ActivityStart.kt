package com.example.mathgameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start.*

class ActivityStart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btn_go.setOnClickListener {

        val intent=Intent(this@ActivityStart,activity_information::class.java)
        startActivity(intent)

        }

        btn_shp.setOnClickListener{
            val intent2 = Intent(this@ActivityStart, Shop::class.java)
            startActivity(intent2)
        }
    }

}
