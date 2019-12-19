package com.example.mathgameapp.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.example.mathgameapp.DBHelper.DBHelper
import com.example.mathgameapp.Model.Shopping

import com.example.mathgameapp.R

class Sonuc : DialogFragment() {

    var dogrular=0
    var yanlislar=0
    var coinler=0

     lateinit var tvf_dogruSayisi:TextView
     lateinit var tvf_yanlisSayisi:TextView
     lateinit var tvf_kazanilanCoin:TextView
    internal  lateinit var db:DBHelper
    internal  var lstBilgiler:List<Shopping> = ArrayList<Shopping>()

    override fun show(transaction: FragmentTransaction?, tag: String?): Int {
        transaction!!.commitAllowingStateLoss()
        return transaction!!.commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_dialog, container, false)

        tvf_dogruSayisi = view.findViewById<TextView>(R.id.txt_dogru_sayisi)
        tvf_yanlisSayisi = view.findViewById<TextView>(R.id.txt_yanlis_sayisi)
        tvf_kazanilanCoin = view.findViewById<TextView>(R.id.txt_kazanilan_coins)
        tvf_dogruSayisi.text=dogrular.toString()
        tvf_yanlisSayisi.text=yanlislar.toString()
        tvf_kazanilanCoin.text=coinler.toString()
        var btn_ok = view.findViewById<Button>(R.id.btn_tamam)

        db= DBHelper(activity!!)

        btn_ok.setOnClickListener {
            val  shopping = Shopping(1,"Adam",1,coinler,0,0)
            lstBilgiler=db.tumBilgiler
            if (lstBilgiler.size==0){
                db.addUser(shopping)
                Log.e("DBKontrol","Kayıt Ekleme Alanı Çalıştı")
            }else{
                lstBilgiler.forEach {
                    if (shopping.id!=it.id){
                        db.addUser(shopping)
                        Log.e("DBKontrol","Kayıt Ekleme Alanı Çalıştı2")
                    }
                    else{
                        var hesaptakiCoin=it.coin
                        Log.e("DBKontrol","Hesaptaki coin: $hesaptakiCoin")
                        var eklenecekCoin=hesaptakiCoin+coinler
                        var shop = Shopping(1,"Adam",1,eklenecekCoin,it.sure_gel,it.coin_gel)
                        db.updateUser(shop)
                        Log.e("DBKontrol","Kayıt Update Alanı Çalıştı")
                    }
                }
            }

            lstBilgiler.forEach {
                Log.e("DBKontrol","Veritabanındaki kişiler= ${it.id} + ${it.coin} + ${it.coin_gel} + ${it.sure_gel}")
            }

            dialog.dismiss()
            activity!!.onBackPressed()

        }


        return view
    }
}