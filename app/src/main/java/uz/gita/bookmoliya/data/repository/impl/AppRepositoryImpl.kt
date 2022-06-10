package uz.gita.bookmoliya.data.repository.impl

import androidx.room.Room
import uz.gita.bookmoliya.app.App
import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.LocalStorage
import uz.gita.bookmoliya.data.local.database.AppDatabase
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.data.local.database.ThemesEntity
import uz.gita.bookmoliya.data.repository.AppRepository

class AppRepositoryImpl : AppRepository {
    private val database = Room.databaseBuilder(App.instance, AppDatabase::class.java, "db.db")
        .createFromAsset("db/db.db")
        .allowMainThreadQueries()
        .build()
    private val dao = database.getDao()
    private val storage = LocalStorage(App.instance)
    private var listCategory: List<CategoryEntity> = ArrayList<CategoryEntity>()
    private var listThemes: List<ThemesEntity> = dao.allThemes()
    private var listChapter: ArrayList<Chapter> = ArrayList<Chapter>()

    override fun getAll(): List<Chapter> {
        listChapter.clear()
        listCategory = dao.allCategory()
        val themes = listThemes as ArrayList<ThemesEntity>
        for (category in listCategory) {
            var list = ArrayList<ThemesEntity>()
            for (item in themes) {
                if (category.id == item.idCategory) {
                    list.add(item)
                }
            }
            listChapter.add(
                Chapter(
                    category.id,
                    category.name,
                    category.favorite,
                    category.picture,
                    list
                )
            )
        }
        return listChapter
    }

    override fun updateFavorite(categoryEntity: CategoryEntity) {
        dao.updateFavorite(categoryEntity)
    }

    override fun getFavorites(): List<Chapter> {
        listChapter.clear()
        listCategory = dao.allFavorites()
        val themes = listThemes as ArrayList<ThemesEntity>
        for (category in listCategory) {
            var list = ArrayList<ThemesEntity>()
            for (theme in themes) {
                if (category.id == theme.idCategory) {
                    list.add(theme)
                }
            }
            listChapter.add(
                Chapter(
                    category.id,
                    category.name,
                    category.favorite,
                    category.picture,
                    list
                )
            )
        }
        return listChapter
    }

    override fun search(text: String?): List<Chapter> {
        listChapter.clear()
        listCategory = dao.search("%$text%")
        val themes = listThemes as ArrayList<ThemesEntity>
        for (category in listCategory) {
            var list = ArrayList<ThemesEntity>()
            for (theme in themes) {
                if (category.id == theme.idCategory) {
                    list.add(theme)
                }
            }
            listChapter.add(
                Chapter(
                    category.id,
                    category.name,
                    category.favorite,
                    category.picture,
                    list
                )
            )
        }
        return listChapter
    }

    override fun getTheme(id: Int): ThemesEntity {
        return dao.getTheme(id)
    }

    override fun getCategory(idCategory: Int): CategoryEntity {
        return dao.getCategory(idCategory)
    }

    override fun getChapter(idCategory: Int): Chapter {
        val category = dao.getCategory(idCategory)
        return Chapter(
            category.id,
            category.name,
            category.favorite,
            category.picture,
            dao.getThemes(idCategory)
        )
    }

    override var textSize: Int
        get() = storage.textSize
        set(value) {
            storage.textSize = value
        }

    override var isNightMode: Boolean
        get() = storage.isNightMode
        set(value) {
            storage.isNightMode = value
        }
}