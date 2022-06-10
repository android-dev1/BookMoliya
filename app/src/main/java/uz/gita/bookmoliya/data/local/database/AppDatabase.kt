package uz.gita.bookmoliya.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [CategoryEntity::class, ThemesEntity::class], version=3)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao():Dao
}