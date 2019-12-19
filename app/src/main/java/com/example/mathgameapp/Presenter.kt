package com.example.mathgameapp

class Presenter: MainPresenter , CardInterator.OnFinishedListener{

    private lateinit var listItems: ArrayList<CardItem>
    private var view: MainView
    private var mainIterator: CardInterator
    private var countClic = 0

    constructor(mainView: HafizaOyunu){
        this.view = mainView
        this.mainIterator = CardInterator()
        mainIterator.requestData(this)
    }

    override fun onResultSucess(listOKCard: ArrayList<CardItem>) {
        this.listItems = listOKCard
        view.requestData(listItems)
    }

    override fun onResultFail(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun validateClikcs(cardOption: Int, indexArray: Int) {
        ++countClic
        if (LogicMemory.clickOnCard(cardOption, indexArray)){
            view.cardClickOk(cardOption)
        }
        if (countClic == 2){
            view.cardClickFail()
            countClic = 0
        }
    }

}