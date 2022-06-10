package uz.gita.bookmoliya.model

import uz.gita.bookmoliya.data.Chapter

interface HomeModel {

    fun getAllChapter():List<Chapter>

    fun search(text:String):List<Chapter>
}