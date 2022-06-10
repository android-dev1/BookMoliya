package uz.gita.bookmoliya.ui.screen

import uz.gita.bookmoliya.data.Chapter

interface HomeView {
    fun showChapters(listChapters:List<Chapter>)
    fun firstShowChapter(listChapters: List<Chapter>)
    fun setShare()
    fun showDialog()
    fun transferChapter(id: Int)
}