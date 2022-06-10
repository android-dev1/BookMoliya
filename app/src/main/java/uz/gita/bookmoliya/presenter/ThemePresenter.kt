package uz.gita.bookmoliya.presenter

import uz.gita.bookmoliya.ui.screen.ThemeView

interface ThemePresenter {
    var isNightState:Boolean
    fun attachView(view:ThemeView)
    fun detachView()
    fun joinTheme(id:Int)
    fun onTransferPicture(idCategory: Int)
    fun onTransferChapter(idCategory: Int)
    fun onSetting()
    fun onIncrementTextSize():Boolean
    fun onDecrementTextSize():Boolean
    fun joinTextSize()
}