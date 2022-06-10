package uz.gita.bookmoliya.data.local

import android.content.Context
import uz.gita.bookmoliya.utils.SharedPreference

class LocalStorage(context: Context) : SharedPreference(context) {
    var textSize: Int by IntPreference(16)
    var isNightMode:Boolean by BooleanPreference(false)
}