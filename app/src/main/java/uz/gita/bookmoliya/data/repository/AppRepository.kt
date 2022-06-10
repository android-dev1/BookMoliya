package uz.gita.bookmoliya.data.repository

import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.data.local.database.ThemesEntity

interface AppRepository {

    fun getAll(): List<Chapter>

    fun updateFavorite(categoryEntity: CategoryEntity)

    fun getFavorites(): List<Chapter>

    fun search(text: String?): List<Chapter>

    fun getTheme(id:Int):ThemesEntity

    fun getCategory(idCategory:Int):CategoryEntity

    fun getChapter(idCategory: Int):Chapter

    var textSize:Int

    var isNightMode:Boolean
}