package uz.gita.bookmoliya.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "themes")
data class ThemesEntity(
    @PrimaryKey
    val id: Int,
    val theme: String,
    val file: String,
    val text: String?,
    @ColumnInfo(name = "id_cat")
    val idCategory: Int
)