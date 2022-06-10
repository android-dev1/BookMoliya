package uz.gita.bookmoliya.presenter.impl

import android.util.Log
import uz.gita.bookmoliya.model.HomeModel
import uz.gita.bookmoliya.presenter.HomePresenter
import uz.gita.bookmoliya.ui.screen.HomeView

class HomePresenterImpl
    (private val model: HomeModel) : HomePresenter {
    private var view: HomeView? = null

    override fun attachView(view: HomeView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun onSearch(text: String) {
        this.view?.showChapters(model.search(text))
    }

    override fun onShare() {
        this.view?.setShare()
    }

    override fun onAbout() {
        this.view?.showDialog()
    }

    override fun joinChapter() {
        this.view?.firstShowChapter(model.getAllChapter())
    }

    override fun transfer(id: Int) {
        this.view?.transferChapter(id)
    }
}