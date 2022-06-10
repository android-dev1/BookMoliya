package uz.gita.bookmoliya.ui.screen.impl

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookmoliya.R
import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.databinding.FragmentSelectedBinding
import uz.gita.bookmoliya.model.impl.SelectedModelImpl
import uz.gita.bookmoliya.presenter.SelectedPresenter
import uz.gita.bookmoliya.presenter.impl.SelectedPresenterImpl
import uz.gita.bookmoliya.ui.adapter.FavoriteChapterAdapter
import uz.gita.bookmoliya.ui.screen.SelectedView

class SelectedFragment : Fragment(R.layout.fragment_selected), SelectedView {
    private val binding by viewBinding(FragmentSelectedBinding::bind)
    private val presenter: SelectedPresenter = SelectedPresenterImpl(SelectedModelImpl())
    private lateinit var adapter: FavoriteChapterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            val onClickFavoriteListener: (CategoryEntity) -> Unit = {
                presenter.joinStateFavoriteChapter(it)
            }
            adapter = FavoriteChapterAdapter(onClickFavoriteListener) {
                presenter.transfer(it)
            }
            rvContainerChapterWithLessons.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvContainerChapterWithLessons.adapter = adapter
            presenter.joinChapter()
        }
    }

    override fun showFavoriteChapters(listChapters: List<Chapter>) {
        if (listChapters.isEmpty()) {
            binding.lineNotFavorite.visibility = View.VISIBLE
        } else binding.lineNotFavorite.visibility = View.INVISIBLE
        adapter.submitList(listChapters)
    }

    override fun transferChapter(id: Int) {
        val intent = Intent(requireContext(), ChapterActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}