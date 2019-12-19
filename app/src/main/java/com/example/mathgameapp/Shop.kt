package com.example.mathgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mathgameapp.DBHelper.DBHelper
import com.example.mathgameapp.Model.Shopping
import kotlinx.android.synthetic.main.activity_shop.*

class Shop : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal var lstBilgiler:List<Shopping> = ArrayList<Shopping>()
    internal var coinMiktari=0
    internal var sureGelisimi=0
    internal var coinGelisimi=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        db= DBHelper(this)
        lstBilgiler = db.tumBilgiler

        if (lstBilgiler.size != 0){
            lstBilgiler.forEach {
                coinMiktari=it.coin
                sureGelisimi=it.sure_gel
                coinGelisimi=it.coin_gel
            }
        }else{
            tv_coin_shop.text="0"
        }

        VerileriYukle()

        btn_coin_gelistir.setOnClickListener {
            if (coinMiktari>=5000){
                coinMiktari-=5000
                coinGelisimi+=1
                pb_coin_gelisimi.progress=coinGelisimi
                tv_coin_shop.text=coinMiktari.toString()
                var shop = Shopping(1,"Adam",1,coinMiktari,sureGelisimi,coinGelisimi)
                db.updateUser(shop)

            }else{
                Toast.makeText(this,"Geliştirme için hesabında yeterli bakiye yok!", Toast.LENGTH_LONG).show()
            }
        }

        btn_sure_gelistir.setOnClickListener {
            if (coinMiktari>=3000){
                coinMiktari-=3000
                sureGelisimi+=1
                pb_sure_gelisimi.progress=sureGelisimi
                tv_coin_shop.text=coinMiktari.toString()
                var shop = Shopping(1,"Adam",1,coinMiktari,sureGelisimi,coinGelisimi)
                db.updateUser(shop)
            }else{
                Toast.makeText(this,"Geliştirme için hesabında yeterli bakiye yok!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun VerileriYukle() {
        tv_coin_shop.text=coinMiktari.toString()
        pb_coin_gelisimi.max=5
        pb_sure_gelisimi.max=5
        pb_sure_gelisimi.progress=sureGelisimi
        pb_coin_gelisimi.progress=coinGelisimi

        if (sureGelisimi==5){
            btn_sure_gelistir.isEnabled=false
        }
        if (coinGelisimi==5){
            btn_coin_gelistir.isEnabled=false
        }
    }
}
