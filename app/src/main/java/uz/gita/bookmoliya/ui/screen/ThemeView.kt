package uz.gita.bookmoliya.ui.screen

interface ThemeView {
    fun transferPicture(idCategory: Int)
    fun transferChapter(idCategory: Int)
    fun showSettings()
    fun initTheme(name: String, text: String)
    fun setTextSize(textSize:Int)
}