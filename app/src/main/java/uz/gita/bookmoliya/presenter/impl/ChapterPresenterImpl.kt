package uz.gita.bookmoliya.presenter.impl

import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.model.ChapterModel
import uz.gita.bookmoliya.presenter.ChapterPresenter
import uz.gita.bookmoliya.ui.screen.ChapterView

class ChapterPresenterImpl(private val model: ChapterModel) : ChapterPresenter {
    private var view: ChapterView? = null
    private var isFavorite = false
    private var chapter: Chapter? = null

    override fun attachView(view: ChapterView) {
        this.view = view
    }

    override fun detachView() {
        chapter?.let {
            val fav = if (isFavorite) {
                1
            }else 0
            model.updateFavorite(CategoryEntity(it.id, it.name, fav, it.picture))
        }
        this.view = null
    }

    override fun joinChapter(idCategory: Int) {
        chapter = model.getChapter(idCategory)
        chapter?.let {
            isFavorite = it.favorite != 0
            this.view?.showFavorite(isFavorite)
            this.view?.showChapter(it)
        }
    }

    override fun transfer(idCategory: Int,idTheme: Int) {
        this.view?.transferTheme(idCategory,idTheme)
    }

    override fun transfer() {
        chapter?.let {
            this.view?.transferTheme(it.id,it.themes[0].id)
        }
    }

    override fun onFavorite() {
        isFavorite = !isFavorite
        this.view?.showFavorite(isFavorite)
    }
}