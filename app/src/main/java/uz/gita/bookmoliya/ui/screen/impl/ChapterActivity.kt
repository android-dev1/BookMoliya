package uz.gita.bookmoliya.ui.screen.impl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.bookmoliya.R
import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.databinding.ActivityChapterBinding
import uz.gita.bookmoliya.model.impl.ChapterModelImpl
import uz.gita.bookmoliya.presenter.ChapterPresenter
import uz.gita.bookmoliya.presenter.impl.ChapterPresenterImpl
import uz.gita.bookmoliya.ui.adapter.ThemeAdapter
import uz.gita.bookmoliya.ui.screen.ChapterView

class ChapterActivity : AppCompatActivity(), ChapterView {
    private lateinit var binding: ActivityChapterBinding
    private lateinit var presenter: ChapterPresenter
    private lateinit var adapter: ThemeAdapter
    private var idCategory = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter=ChapterPresenterImpl(ChapterModelImpl())
        idCategory = intent.getIntExtra("id", 1)
        presenter.attachView(this)
        initView()
    }

    private fun initView() {
        binding.apply {
            val onClickThemeListener: (Int, Int) -> Unit = {idCategory, id->Int
                presenter.transfer(idCategory,id)
            }

            adapter = ThemeAdapter(onClickThemeListener)
            rvContainerTheme.layoutManager =
                LinearLayoutManager(this@ChapterActivity, LinearLayoutManager.VERTICAL, false)
            rvContainerTheme.adapter = adapter
            presenter.joinChapter(idCategory)

            imgBtnBack.setOnClickListener {
                finish()
            }
            imgBtnFavorite.setOnClickListener {
                presenter.onFavorite()
            }
            btnRead.setOnClickListener {
                presenter.transfer()
            }
        }
    }

    override fun showChapter(chapter: Chapter) {
        binding.apply {
            tvChapterName.text = chapter.name
            adapter.submitList(chapter.themes)
        }
    }

    override fun transferTheme(idCategory: Int, idTheme: Int) {
        val intent = Intent(this, ThemeActivity::class.java)
        intent.putExtra("id_", idCategory)
        intent.putExtra("id", idTheme)
        startActivity(intent)
        finish()
    }

    override fun showFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.imgBtnFavorite.setImageResource(R.drawable.ic_bookmark_click)
        } else binding.imgBtnFavorite.setImageResource(R.drawable.ic_bookmark)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}