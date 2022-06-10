package uz.gita.bookmoliya.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.databinding.ItemChapterWithThemeBinding
import uz.gita.bookmoliya.ui.holder.ChapterHolder

class ChapterAdapter(
    private val onclickOnChapterListener: ((Int) -> Unit)
) :
    ListAdapter<Chapter, ChapterHolder>(object :
        DiffUtil.ItemCallback<Chapter>() {
        override fun areItemsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem.name==newItem.name && oldItem.picture==newItem.picture
        }

        override fun areContentsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterHolder {
        val view =
            ItemChapterWithThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChapterHolder(view, onclickOnChapterListener)
    }

    override fun onBindViewHolder(holder: ChapterHolder, position: Int) {
        holder.bind(getItem(position))
    }
}