package uz.gita.bookmoliya.presenter

import uz.gita.bookmoliya.ui.screen.HomeView

interface HomePresenter {

    fun attachView(view:HomeView)

    fun detachView()

    fun joinChapter()

    fun transfer(id: Int)

    fun onShare()

    fun onAbout()

    fun onSearch(text:String)
}