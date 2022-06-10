package uz.gita.bookmoliya.model

interface ThemeModel {
    var isNightMode:Boolean
    fun getTheme(id: Int): String
    @Throws(Exception::class)
    fun getText(): String
    fun getTextSize():Int
    fun setTextSize(isUpper:Boolean)
}