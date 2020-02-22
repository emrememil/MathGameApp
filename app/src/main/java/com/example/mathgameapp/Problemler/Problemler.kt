package com.example.mathgameapp.Problemler

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginLeft
import com.example.mathgameapp.R
import kotlinx.android.synthetic.main.activity_information.view.*
import kotlinx.android.synthetic.main.activity_problemler.*
import kotlinx.android.synthetic.main.activity_selected.view.*

class Problemler : AppCompatActivity() {
    var toplamDeger = 0
    var genelPuan = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problemler)

        problemOlustur()

        et_cevap.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if((s.toString()!!.equals(toplamDeger.toString()))){
                    Toast.makeText(this@Problemler,"Doğru Cevap", Toast.LENGTH_LONG).show()
                    problemOlustur()
                    et_cevap.text=null
                    genelPuan+=50
                    tv_puan2.text =genelPuan.toString()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun problemOlustur() {
        temizle()
        OruntuYukle(4,oruntu1)
        OruntuYukle(1,oruntu2)
        OruntuYukle(2,oruntu3)
        SonOruntuyuYukle(3,oruntu4)
        //Toast.makeText(this@Problemler,"$toplamDeger", Toast.LENGTH_LONG).show()
    }

    private fun temizle() {
        toplamDeger=0
        oruntu1.removeAllViews()
        oruntu2.removeAllViews()
        oruntu3.removeAllViews()
        oruntu4.removeAllViews()
    }

    private fun OruntuYukle(resimSayisi:Int, layout: LinearLayout) {
        var image = Images()
        var images = image.imagesA
        var toplamDeger=0
        for (i in 1..resimSayisi){

            val imageView = ImageView(this)
            val tv = TextView(this)
            val tv2 = TextView(this)

            tv.text="+"
            tv.setTextSize(getResources().getDimension(R.dimen.textsize))

            tv2.text="="
            tv2.setTextSize(getResources().getDimension(R.dimen.textsize))

            imageView.layoutParams = LinearLayout.LayoutParams(96, 96)
            imageView.setImageResource(images[i-1].yol)

            toplamDeger+=images[i-1].deger

            imageView.SetMargin(leftMargin = 2)

            layout.addView(imageView)
            layout.addView(tv)
            if (i==resimSayisi){
                val textView = TextView(this)
                textView.text="$toplamDeger"
                textView.setTextSize(getResources().getDimension(R.dimen.textsize))
                textView.layoutParams = LinearLayout.LayoutParams(150,80)
                textView.SetMargin(leftMargin = 12)
                layout.removeView(tv)
                layout.addView(tv2)
                layout.addView(textView)

            }
        }
    }

    private fun SonOruntuyuYukle(resimSayisi:Int, layout: LinearLayout) {
        var image = Images()
        var images = image.imagesA

        for (i in 1..resimSayisi) {

            val imageView = ImageView(this)
            val tv = TextView(this)
            val tv2 = TextView(this)

            tv.text = "+"
            tv.setTextSize(getResources().getDimension(R.dimen.textsize))

            tv2.text = "="
            tv2.setTextSize(getResources().getDimension(R.dimen.textsize))

            imageView.layoutParams = LinearLayout.LayoutParams(96, 96)
            imageView.setImageResource(images[i - 1].yol)

            toplamDeger += images[i - 1].deger

            imageView.SetMargin(leftMargin = 2)

            layout.addView(imageView)
            layout.addView(tv)
            if (i == resimSayisi) {
                /*val editText = EditText(this)
                editText.setTextSize(getResources().getDimension(R.dimen.textsize))
                editText.layoutParams = LinearLayout.LayoutParams(150, 80)
                editText.SetMargin(leftMargin = 12)*/
                val textView = TextView(this)
                textView.text="?"
                textView.setTextSize(getResources().getDimension(R.dimen.textsize))
                textView.layoutParams = LinearLayout.LayoutParams(150,80)
                textView.SetMargin(leftMargin = 12)
                layout.removeView(tv)
                layout.addView(tv2)
                layout.addView(textView)

            }
        }
    }

    fun View.SetMargin(leftMargin: Int? = null, topMargin: Int? = null,
                  rightMargin: Int? = null, bottomMargin: Int? = null) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(
            leftMargin ?: params.leftMargin,
            topMargin ?: params.topMargin,
            rightMargin ?: params.rightMargin,
            bottomMargin ?: params.bottomMargin)
        layoutParams = params
    }


}
