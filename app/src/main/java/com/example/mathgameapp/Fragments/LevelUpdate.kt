package com.example.mathgameapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.mathgameapp.R

class LevelUpdate : DialogFragment() {
    var seviye=0
    lateinit var tv_seviye:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_level_update, container, false)
        tv_seviye = view.findViewById<TextView>(R.id.tv_seviye)
        var btn_ok = view.findViewById<Button>(R.id.btn_ok)
        tv_seviye.text=seviye.toString()
        btn_ok.setOnClickListener {
            dialog.dismiss()
        }
        return view
    }
}



