package uz.gita.bookmoliya.ui.holder

import androidx.recyclerview.widget.RecyclerView
import uz.gita.bookmoliya.data.local.database.ThemesEntity
import uz.gita.bookmoliya.databinding.ItemThemeBinding

class ThemeHolder(
    private val binding: ItemThemeBinding,
    private val onclickThemeListener: ((Int, Int) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ThemesEntity) {
        binding.apply {
            lineTheme.setOnClickListener {
                onclickThemeListener(item.idCategory, item.id)
            }
            tvTheme.text = "${item.file}.${item.theme}"
        }
    }
}