package uz.gita.bookmoliya.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.databinding.ItemChapterWithThemeBinding
import uz.gita.bookmoliya.ui.holder.FavoriteChapterHolder

class FavoriteChapterAdapter(
    private val onclickOnFavorite: ((CategoryEntity) -> Unit),
    private val onclickOnChapterListener: ((Int) -> Unit)
) :
    ListAdapter<Chapter, FavoriteChapterHolder>(object :
        DiffUtil.ItemCallback<Chapter>() {
        override fun areItemsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteChapterHolder {
        val view =
            ItemChapterWithThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteChapterHolder(view,onclickOnFavorite, onclickOnChapterListener)
    }


    override fun onBindViewHolder(holder: FavoriteChapterHolder, position: Int) {
        holder.bind(getItem(position))
    }
}