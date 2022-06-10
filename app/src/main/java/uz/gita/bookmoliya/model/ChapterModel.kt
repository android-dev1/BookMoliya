package uz.gita.bookmoliya.model

import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity

interface ChapterModel {
    fun getChapter(idCategory:Int): Chapter
    fun updateFavorite(categoryEntity: CategoryEntity)
}