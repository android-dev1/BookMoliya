package uz.gita.bookmoliya.ui.screen

import uz.gita.bookmoliya.data.Chapter

interface SelectedView {
    fun showFavoriteChapters(listChapters:List<Chapter>)
    fun transferChapter(id: Int)
}