package uz.gita.bookmoliya.presenter

import uz.gita.bookmoliya.ui.screen.ChapterPictureView
import uz.gita.bookmoliya.ui.screen.HomeView

interface ChapterPicturePresenter {

    fun attachView(view: ChapterPictureView)

    fun detachView()

    fun joinPicture(index:Int)

    fun onNext()

    fun onPrev()
}