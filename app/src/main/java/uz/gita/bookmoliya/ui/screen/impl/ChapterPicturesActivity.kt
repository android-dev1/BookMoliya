package uz.gita.bookmoliya.ui.screen.impl

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.gita.bookmoliya.databinding.ActivityChapterPicturesBinding
import uz.gita.bookmoliya.model.impl.ChapterPictureModelImpl
import uz.gita.bookmoliya.presenter.ChapterPicturePresenter
import uz.gita.bookmoliya.presenter.impl.ChapterPicturePresenterImpl
import uz.gita.bookmoliya.ui.screen.ChapterPictureView

class ChapterPicturesActivity: AppCompatActivity(), ChapterPictureView {
    private lateinit var binding:ActivityChapterPicturesBinding
    private lateinit var presenter:ChapterPicturePresenter
    private var idCategory=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChapterPicturesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idCategory=intent.getIntExtra("id",0)
        presenter=ChapterPicturePresenterImpl(ChapterPictureModelImpl(idCategory))
        presenter.attachView(this)
        presenter.joinPicture(0)
        initView()
    }

    private fun initView() {
        binding.apply {
            imgBtnClose.setOnClickListener {
                finish()
            }
            imgPrevPicture.setOnClickListener {
                presenter.onPrev()
            }
            imgNextPicture.setOnClickListener {
                presenter.onNext()
            }
        }
    }

    override fun showPicture(bitmap: Bitmap?) {
        binding.imgPictures.setImageBitmap(bitmap)
    }

    override fun setEnabledPrev(isEnable: Boolean) {
        binding.imgPrevPicture.isEnabled=isEnable
    }

    override fun setEnabledNext(isEnable: Boolean) {
        binding.imgNextPicture.isEnabled=isEnable
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        finish()
    }
}