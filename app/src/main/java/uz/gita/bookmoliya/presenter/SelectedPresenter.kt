package uz.gita.bookmoliya.presenter

import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.ui.screen.SelectedView

interface SelectedPresenter {
    fun attachView(view: SelectedView)

    fun detachView()

    fun joinChapter()

    fun transfer(id: Int)

    fun joinStateFavoriteChapter(categoryEntity: CategoryEntity)
}