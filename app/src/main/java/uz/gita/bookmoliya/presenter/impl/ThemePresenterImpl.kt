package uz.gita.bookmoliya.presenter.impl

import uz.gita.bookmoliya.model.ThemeModel
import uz.gita.bookmoliya.presenter.ThemePresenter
import uz.gita.bookmoliya.ui.screen.ThemeView
import java.lang.Exception

class ThemePresenterImpl(private val model: ThemeModel) : ThemePresenter {
    private var view: ThemeView? = null
    override var isNightState: Boolean
        get() = model.isNightMode
        set(value) {
            model.isNightMode = value
        }

    override fun attachView(view: ThemeView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun joinTheme(id: Int) {
        try {
            this.view?.initTheme(model.getTheme(id), model.getText())
            this.view?.setTextSize(model.getTextSize())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onTransferPicture(idCategory: Int) {
        this.view?.transferPicture(idCategory)
    }

    override fun onTransferChapter(idCategory: Int) {
        this.view?.transferChapter(idCategory)
    }

    override fun onSetting() {
        this.view?.showSettings()
    }

    override fun joinTextSize() {
        this.view?.setTextSize(model.getTextSize())
    }

    override fun onIncrementTextSize(): Boolean {
        model.setTextSize(true)
        joinTextSize()
        return model.getTextSize()>=22
    }

    override fun onDecrementTextSize(): Boolean {
        model.setTextSize(false)
        joinTextSize()
        return model.getTextSize()<=12
    }
}