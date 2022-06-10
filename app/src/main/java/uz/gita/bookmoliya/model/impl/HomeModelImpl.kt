package uz.gita.bookmoliya.model.impl

import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.repository.AppRepository
import uz.gita.bookmoliya.data.repository.impl.AppRepositoryImpl
import uz.gita.bookmoliya.model.HomeModel

class HomeModelImpl : HomeModel {
    private val repository: AppRepository = AppRepositoryImpl()

    override fun getAllChapter(): List<Chapter> {
        return repository.getAll()
    }

    override fun search(text: String): List<Chapter> {
        return repository.search(text.uppercase())
    }
}