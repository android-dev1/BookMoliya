package uz.gita.bookmoliya.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val favorite: Int,
    val picture: String
)