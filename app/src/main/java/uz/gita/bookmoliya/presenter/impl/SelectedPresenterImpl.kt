package uz.gita.bookmoliya.presenter.impl

import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.model.SelectedModel
import uz.gita.bookmoliya.presenter.SelectedPresenter
import uz.gita.bookmoliya.ui.screen.SelectedView

class SelectedPresenterImpl(private val model: SelectedModel)
    : SelectedPresenter {
    private var view: SelectedView? = null
    override fun attachView(view: SelectedView) {
        this.view=view
    }

    override fun detachView() {
        this.view=null
    }

    override fun joinChapter() {
        this.view?.showFavoriteChapters(model.getAllFavorite())
    }

    override fun joinStateFavoriteChapter(categoryEntity: CategoryEntity) {
        model.updateFavorite(categoryEntity)
        joinChapter()
    }

    override fun transfer(id: Int) {
        this.view?.transferChapter(id)
    }
}