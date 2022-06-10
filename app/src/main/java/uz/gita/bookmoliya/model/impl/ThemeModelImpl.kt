package uz.gita.bookmoliya.model.impl

import android.util.Log
import uz.gita.bookmoliya.app.App
import uz.gita.bookmoliya.data.local.database.ThemesEntity
import uz.gita.bookmoliya.data.repository.AppRepository
import uz.gita.bookmoliya.data.repository.impl.AppRepositoryImpl
import uz.gita.bookmoliya.model.ThemeModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class ThemeModelImpl : ThemeModel {
    private val repository: AppRepository = AppRepositoryImpl()
    private lateinit var theme: ThemesEntity
    private var reader: BufferedReader? = null
    override var isNightMode: Boolean
        get() = repository.isNightMode
        set(value) {repository.isNightMode=value}

    override fun getTheme(id: Int): String {
        theme = repository.getTheme(id)
        return theme.theme
    }

    @Throws(Exception::class)
    override fun getText(): String {
        val text: StringBuilder = StringBuilder()
        val reader = BufferedReader(
            InputStreamReader(App.instance.assets.open("${theme.file}.txt"))
        )

        var mLine = reader.readLines()
        for (i in mLine) {
            text.append(i)
            text.append("\n")
        }
        closeReader()
        Log.d("111", "getText:${text.toString().length} ")
        return text.toString()
    }

    override fun getTextSize(): Int {
        return repository.textSize
    }

    override fun setTextSize(isUpper: Boolean) {
        if (isUpper && repository.textSize < 22) {
            repository.textSize += 2
        } else {
            if (repository.textSize > 12) {
                repository.textSize -= 2
            }
        }
    }

    private fun closeReader() {
        reader?.let {
            try {
                it.close();
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}