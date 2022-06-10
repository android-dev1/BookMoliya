package uz.gita.bookmoliya.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import uz.gita.bookmoliya.data.Chapter
import uz.gita.bookmoliya.data.local.database.CategoryEntity
import uz.gita.bookmoliya.databinding.ItemChapterWithThemeBinding

class FavoriteChapterHolder(
    private val binding: ItemChapterWithThemeBinding,
    private val onclickOnFavorite: ((CategoryEntity) -> Unit),
    private val onclickOnChapterListener: ((Int) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Chapter) {
        binding.apply {
            imgBtnFavorite.visibility = View.VISIBLE
            imgBtnFavorite.setOnClickListener {
                val categoryEntity=CategoryEntity(item.id,
                item.name,
                0,
                item.picture)
                onclickOnFavorite(categoryEntity)

                bindingAdapter?.notifyItemRemoved(absoluteAdapterPosition)
            }
            constContainerChapterWithTheme.setOnClickListener {
                onclickOnChapterListener(item.id)
            }
            var text=item.name[0].toString()
            text+=item.name.substring(1, item.name.length).lowercase()
            tvChapter.text = text
            tvTheme1.text = "${item.themes[0].file}.${item.themes[0].theme}"
            tvTheme2.text = "${item.themes[1].file}.${item.themes[1].theme}"
            tvTheme3.text = "${item.themes[2].file}.${item.themes[2].theme}"
        }
    }
}