package uz.gita.bookmoliya.presenter.impl

import uz.gita.bookmoliya.model.ChapterPictureModel
import uz.gita.bookmoliya.presenter.ChapterPicturePresenter
import uz.gita.bookmoliya.ui.screen.ChapterPictureView
import java.io.IOException

class ChapterPicturePresenterImpl (private val model:ChapterPictureModel): ChapterPicturePresenter {
    private var view:ChapterPictureView?=null
    private var isOpen=false

    override fun attachView(view: ChapterPictureView) {
        this.view=view
        isOpen = try {
            model.openReader()
            true
        }catch (e:IOException){
            e.printStackTrace()
            false
        }
    }

    override fun detachView() {
        try{
            model.closeReader()
        }catch (e:IOException){
            e.printStackTrace()
        }
        this.view=null
    }

    override fun joinPicture(index:Int) {
        if (isOpen) {
            this.view?.showPicture(model.showPage(index))
        }
    }

    override fun onNext() {
        if(isOpen) {
            joinPicture(model.onNextDocClick())
            this.view?.setEnabledNext(model.isEnabledNext())
            this.view?.setEnabledPrev(model.isEnabledPrev())
        }
    }

    override fun onPrev() {
        if(isOpen) {
            joinPicture(model.onPreviousDocClick())
            this.view?.setEnabledPrev(model.isEnabledPrev())
            this.view?.setEnabledNext(model.isEnabledNext())
        }
    }
}