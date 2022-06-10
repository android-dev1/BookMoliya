package uz.gita.bookmoliya.ui.screen

import android.graphics.Bitmap

interface ChapterPictureView {
    fun showPicture(bitmap: Bitmap?)
    fun setEnabledPrev(isEnable:Boolean)
    fun setEnabledNext(isEnable:Boolean)
}