package com.example.mathgameapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.cards_hide_layout.view.*

class CardAdapter: BaseAdapter {

    var listCards = ArrayList<CardItem>()
    var context: Context? = null
    var hadlerActions: MainView

    constructor(listCards: ArrayList<CardItem>, context: Context?, hadlerActions: MainView) : super() {
        this.listCards = listCards
        this.context = context
        this.hadlerActions = hadlerActions
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val card = this.listCards[p0]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var cardview = inflator.inflate(R.layout.cards_hide_layout, null)

        cardview.setOnClickListener { view : View? ->

            //Al clic se descubre la imagen
            cardview.card_item.setImageResource(card.imgInit)
            hadlerActions.sendActionMain(card.imgInit, p0)

        }

        if (card.isVisible){
            cardview.card_item.setImageResource(card.imgInit)
        }else{
            cardview.card_item.setImageResource(card.imgDefault)
        }

        return cardview
    }

    override fun getItem(p0: Int): Any {
        return listCards[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listCards.size
    }
}