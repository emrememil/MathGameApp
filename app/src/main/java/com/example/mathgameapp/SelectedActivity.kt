package com.example.mathgameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selected.*


class SelectedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected)

        btn_hikaye.setOnClickListener {
            val intent=Intent(this@SelectedActivity, MainActivity::class.java)
            startActivity(intent)
        }

        btn_hafiza.setOnClickListener {
            val intent=Intent(this@SelectedActivity, HafizaOyunu::class.java)
            startActivity(intent)
        }
    }
}
