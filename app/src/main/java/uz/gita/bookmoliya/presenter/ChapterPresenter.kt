package uz.gita.bookmoliya.presenter

import uz.gita.bookmoliya.ui.screen.ChapterView

interface ChapterPresenter {
    fun attachView(view: ChapterView)

    fun detachView()

    fun joinChapter(idCategory:Int)

    fun transfer(idCategory: Int, idTheme:Int)

    fun transfer()

    fun onFavorite()

}