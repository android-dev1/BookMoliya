package uz.gita.bookmoliya.model.impl

import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.data.repository.AppRepository
import uz.gita.bookmoliya.data.repository.impl.AppRepositoryImpl
import uz.gita.bookmoliya.model.SelectedModel

class SelectedModelImpl : SelectedModel {
    private val repository: AppRepository = AppRepositoryImpl()

    override fun getAllFavorite(): List<Chapter> {
        return repository.getFavorites()
    }

    override fun updateFavorite(categoryEntity: CategoryEntity) {
        repository.updateFavorite(categoryEntity)
    }
}