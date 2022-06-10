package uz.gita.bookmoliya.data.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    @Query("Select*from category")
    fun allCategory(): List<CategoryEntity>

    @Query("Select*from themes")
    fun allThemes(): List<ThemesEntity>

    @Query("Select*from themes where id=:id")
    fun getTheme(id:Int):ThemesEntity

    @Query("Select*from category where id=:id")
    fun getCategory(id:Int):CategoryEntity

    @Query("Select*from themes where id_cat=:idCategory")
    fun getThemes(idCategory:Int): List<ThemesEntity>

    @Query("Select*from category where favorite=1")
    fun allFavorites(): List<CategoryEntity>

    @Update
    fun updateFavorite(categoryEntity: CategoryEntity)

    @Query("Select*from category where name like:text")
    fun search(text: String?): List<CategoryEntity>

}