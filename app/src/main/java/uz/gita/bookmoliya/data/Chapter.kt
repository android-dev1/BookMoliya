package uz.gita.bookmoliya.data

import uz.gita.bookmoliya.data.local.database.ThemesEntity

data class Chapter(
    val id: Int,
    val name: String,
    val favorite: Int,
    val picture: String,
    val themes: List<ThemesEntity>
)