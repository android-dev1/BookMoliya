package uz.gita.bookmoliya.ui.screen

import uz.gita.bookmoliya.data.Chapter

interface ChapterView {
    fun showChapter(chapter:Chapter)
    fun transferTheme(idCategory:Int,idTheme:Int)
    fun showFavorite(isFavorite:Boolean)
}