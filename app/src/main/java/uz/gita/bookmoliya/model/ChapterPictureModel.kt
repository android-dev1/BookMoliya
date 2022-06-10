package uz.gita.bookmoliya.model

import android.graphics.Bitmap
import java.io.IOException

interface ChapterPictureModel {
    @Throws(IOException::class)
    fun openReader()
    @Throws(IOException::class)
    fun closeReader()
    fun showPage(index:Int):Bitmap?
    fun isEnabledPrev():Boolean
    fun isEnabledNext():Boolean
    fun onNextDocClick():Int
    fun onPreviousDocClick():Int
}