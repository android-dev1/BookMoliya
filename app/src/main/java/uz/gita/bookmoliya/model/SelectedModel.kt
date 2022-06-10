package uz.gita.bookmoliya.model

import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity

interface SelectedModel {

    fun getAllFavorite():List<Chapter>

    fun updateFavorite(categoryEntity: CategoryEntity)
}