package uz.gita.bookmoliya.model.impl

import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.data.repository.AppRepository
import uz.gita.bookmoliya.data.repository.impl.AppRepositoryImpl
import uz.gita.bookmoliya.model.ChapterModel

class ChapterModelImpl : ChapterModel {
    private val repository: AppRepository = AppRepositoryImpl()

    override fun getChapter(idCategory: Int): Chapter {
        return repository.getChapter(idCategory)
    }

    override fun updateFavorite(categoryEntity: CategoryEntity) {
        repository.updateFavorite(categoryEntity)
    }
}