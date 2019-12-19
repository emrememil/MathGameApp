package com.example.mathgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.mathgameapp.DBHelper.DBHelper
import com.example.mathgameapp.Fragments.Sonuc
import com.example.mathgameapp.Model.Shopping
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    internal  lateinit var db: DBHelper
    internal  var lstBilgiler:List<Shopping> = ArrayList<Shopping>()

    var level=1
    var cevap=0
    var dogruSayisi=0
    var toplamDogruSayisi=0
    var yanlisSayisi=0
    var coin=0
    var timeCounter=0
    var timers: CountDownTimer?=null
    var soruyuAtlaFlag=0
    var secenekleriEleFlag=0
    var extraSureFlag=0
    var coinGelisimi=0
    var sureGelisimi=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db= DBHelper(this)
        lstBilgiler=db.tumBilgiler
        lstBilgiler.forEach {
            coinGelisimi=it.coin_gel
            sureGelisimi=it.sure_gel
        }
        soruOlustur()


        btn_soruyu_atla.alpha=0.5f
        btn_secenekleri_ele.alpha=0.5f
        btn_extra_sure.alpha=0.5f

        btn_soruyu_atla.isEnabled=false
        btn_secenekleri_ele.isEnabled=false
        btn_extra_sure.isEnabled=false

        btn_answer1.setOnClickListener(this)
        btn_answer2.setOnClickListener(this)
        btn_answer3.setOnClickListener(this)
        btn_answer4.setOnClickListener(this)
        btn_soruyu_atla.setOnClickListener(this)
        btn_secenekleri_ele.setOnClickListener(this)
        btn_extra_sure.setOnClickListener(this)

    }

    fun progbar2(sure:Int){

        var sayici=sure*1000
        pb_timer.max=sure
        timeCounter=0
         timers= object: CountDownTimer((sayici.toLong()),1000){
            override fun onFinish() {
                setMessage()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeCounter++
                pb_timer.progress= (millisUntilFinished/1000).toInt()

            }

        }.start()
    }
/*
     fun progBar(sure:Int) {
         pb_timer.progress=0
         pb_timer.max = sure
         val timer = Timer()

        timer.scheduleAtFixedRate(object :TimerTask(){

            private var updateUI = object:Handler(){
                override fun dispatchMessage(msg: Message) {
                    super.dispatchMessage(msg)

                    if (timeCounter>sure){
                        setMessage()
                        timeCounter=sure
                        timer.cancel()
                    }
                }
            }
            override fun run() {
                timeCounter++
                pb_timer.progress=timeCounter
                if(timeCounter>sure){
                    timer.cancel()
                    updateUI.sendEmptyMessage(0)
                }
            }

        },1000,1000)

    }*/
    private fun setMessage(){
        Toast.makeText(this@MainActivity,"Süre Bitti", Toast.LENGTH_SHORT).show()
        var dialogGoster= Sonuc()
        dialogGoster.show(supportFragmentManager,"goster")

        dialogGoster.dogrular=toplamDogruSayisi
        dialogGoster.coinler=coin
        dialogGoster.yanlislar=yanlisSayisi
    }

    private fun soruOlustur() {

        var soru=Rastgele()
        val secenekler = mutableSetOf<Button>(btn_answer1,btn_answer2,btn_answer3,btn_answer4)
        var cevaplar = ArrayList<Int>(4)
        secenekler.shuffled()
        soru.soruOlustur(level)
        txt_soru.setText(soru.firstNumber.toString() + " " + soru.sign+ " " + soru.secondNumber.toString())
        cevap=soru.answer

        cevaplar.clear()

        cevaplar.add(secenekUret(cevap))
        cevaplar.add(secenekUret(cevap))
        cevaplar.add(secenekUret(cevap))
        cevaplar.add(cevap)
        cevaplar.shuffle()
        for (i in 0..3){
            var secenek = secenekler.elementAt(i)
            secenek.text= cevaplar[i].toString()
            secenek.visibility=View.VISIBLE
        }
    }

    private fun secenekUret(cevap: Int): Int {
        var uretilenSayi=0
        uretilenSayi= (cevap-10..cevap+10).random()
        if (uretilenSayi!=cevap){
            return uretilenSayi
        }else{
            return (uretilenSayi-3)
        }

    }

    override fun onClick(v: View?) {
        v as Button

        when(v.text){

            cevap.toString() -> {
                ++toplamDogruSayisi
                ++dogruSayisi
                coinBelirle(coinGelisimi)
                Toast.makeText(this,"Doğru Cevap!", Toast.LENGTH_SHORT).show()
                timeCounter=0
                if (timers!=null){
                    timers!!.cancel()
                }
                sureBelirle(sureGelisimi)

                if (level>10){
                    Toast.makeText(this,"Tebrikler! Tüm seviyeyi tamamladınız!", Toast.LENGTH_LONG).show()
                }
                else{
                    if (dogruSayisi>4){
                        ++level
                        dogruSayisi=0
                        tv_level.text=level.toString()
                    }
                    when(level){
                        2->{
                            if (soruyuAtlaFlag!=1){
                                btn_soruyu_atla.isEnabled=true
                                btn_soruyu_atla.alpha=1.0f
                            }else{
                                btn_soruyu_atla.isEnabled=false
                                btn_soruyu_atla.alpha=0.5f
                            }

                        }
                        3->{
                            if (secenekleriEleFlag!=1){
                                btn_secenekleri_ele.isEnabled=true
                                btn_secenekleri_ele.alpha=1.0f
                            }else{
                                btn_secenekleri_ele.isEnabled=false
                                btn_secenekleri_ele.alpha=0.5f
                            }

                        }
                        4->{
                            if (extraSureFlag!=1){
                                btn_extra_sure.isEnabled=true
                                btn_extra_sure.alpha=1.0f
                            }else{
                                btn_extra_sure.isEnabled=false
                                btn_extra_sure.alpha=0.5f
                            }

                        }
                    }
                }
                soruOlustur()
            }
            "Soruyu Atla" -> {
                btn_soruyu_atla.isEnabled=false
                btn_soruyu_atla.alpha=0.5f
                soruyuAtlaFlag=1
                timeCounter=0
                if (timers!=null){

                    timers!!.cancel()
                }
                sureBelirle(sureGelisimi)
                soruOlustur()
            }
            "Seçeneleri Ele" -> {
                btn_secenekleri_ele.isEnabled=false
                btn_secenekleri_ele.alpha=0.5f
                secenekleriEleFlag=1
                if(btn_answer1.text==cevap.toString()){
                    btn_answer2.visibility=View.INVISIBLE
                    btn_answer3.visibility=View.INVISIBLE
                }
                else if (btn_answer2.text==cevap.toString()){
                    btn_answer1.visibility=View.INVISIBLE
                    btn_answer3.visibility=View.INVISIBLE
                }
                else if (btn_answer3.text==cevap.toString()){
                    btn_answer1.visibility=View.INVISIBLE
                    btn_answer2.visibility=View.INVISIBLE
                }
                else if (btn_answer4.text==cevap.toString()){
                    btn_answer1.visibility=View.INVISIBLE
                    btn_answer2.visibility=View.INVISIBLE
                }
            }
            "Extra Süre" -> {
                btn_extra_sure.isEnabled=false
                btn_extra_sure.alpha=0.5f
                extraSureFlag=1
                timeCounter=0
                if (timers!=null){

                    timers!!.cancel()
                }
                sureBelirle(sureGelisimi)
            }
            else -> {
                Toast.makeText(this,"Yanlış Cevap!", Toast.LENGTH_SHORT).show()
                yanlisSayisi++
                coin-=50
                timeCounter=0
                tv_coin_shop.text=coin.toString()
                if (timers!=null){

                    timers!!.cancel()
                }
                sureBelirle(sureGelisimi)
            }
        }

    }

    private fun coinBelirle(coinGelisimi: Int) {
        when(coinGelisimi){
            0-> coin+=50
            1-> coin+=60
            2-> coin+=70
            3-> coin+=80
            4-> coin+=90
            5-> coin+=100
        }
        tv_coin_shop.text=coin.toString()
    }

    private fun sureBelirle(sureGelisimi: Int) {
        when(level){
            0 -> progbar2(5+sureGelisimi)
            1 -> progbar2(10+sureGelisimi)
            2 -> progbar2(15+sureGelisimi)
            3 -> progbar2(20+sureGelisimi)
            4 -> progbar2(20+sureGelisimi)
            5 -> progbar2(25+sureGelisimi)
            6 -> progbar2(25+sureGelisimi)
            7 -> progbar2(25+sureGelisimi)
            8 -> progbar2(25+sureGelisimi)
            9 -> progbar2(25+sureGelisimi)
            10 -> progbar2(25+sureGelisimi)

        }
    }
}
