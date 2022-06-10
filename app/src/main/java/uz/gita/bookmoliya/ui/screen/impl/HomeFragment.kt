package uz.gita.bookmoliya.ui.screen.impl

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookmoliya.R
import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.databinding.FragmentHomeBinding
import uz.gita.bookmoliya.model.impl.HomeModelImpl
import uz.gita.bookmoliya.presenter.HomePresenter
import uz.gita.bookmoliya.presenter.impl.HomePresenterImpl
import uz.gita.bookmoliya.ui.adapter.ChapterAdapter
import uz.gita.bookmoliya.ui.screen.HomeView
import java.lang.reflect.Array

class HomeFragment:Fragment(R.layout.fragment_home),HomeView {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val presenter:HomePresenter=HomePresenterImpl(HomeModelImpl())
    private lateinit var  adapter:ChapterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        initViews()
    }

    private fun initViews(){
        binding.apply {
            imgBtnShare.setOnClickListener {
                presenter.onShare()
            }
            imgBtnAbout.setOnClickListener {
                presenter.onAbout()
            }

            adapter= ChapterAdapter {
                presenter.transfer(it)
            }
            rvContainerChapterWithLessons.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvContainerChapterWithLessons.adapter=adapter
            presenter.joinChapter()
            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query!=null && query!=""){
                        presenter.onSearch(query.trim())
                        Log.d("1111", "onQueryTextChange:$query")
                    } else presenter.joinChapter()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText==null || newText==""){
                        presenter.joinChapter()
                    }
                    return true
                }
            })
        }
    }



    override fun showChapters(listChapters: List<Chapter>) {
        adapter.submitList(listChapters)
        adapter.notifyDataSetChanged()
    }

    override fun firstShowChapter(listChapters: List<Chapter>) {
        adapter.submitList(listChapters)
    }

    override fun setShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "type/plain"
        val body = "Ilovani yuklab olish uchun link"
        val sub = "https://play.google.com/store/apps/details?id=uz.gita.puzzlebyme"
        intent.putExtra(Intent.EXTRA_TEXT, body)
        intent.putExtra(Intent.EXTRA_TEXT, sub)
        startActivity(Intent.createChooser(intent, "Ilovani ulashing!"))
    }

    override fun showDialog() {
        val dialog=Dialog(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_about, null, false)
        val btnClose = view.findViewById<Button>(R.id.btn_about_close)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00FFFFFF")))
        dialog.show()
    }

    override fun transferChapter(id: Int) {
        val intent=Intent(requireContext(), ChapterActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}