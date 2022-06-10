package uz.gita.bookmoliya.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import uz.gita.bookmoliya.data.local.database.ThemesEntity
import uz.gita.bookmoliya.databinding.ItemThemeBinding
import uz.gita.bookmoliya.ui.holder.ThemeHolder

class ThemeAdapter(
    private val onclickThemeListener:( (Int, Int) -> Unit)
) :
    ListAdapter<ThemesEntity, ThemeHolder>(object :
        DiffUtil.ItemCallback<ThemesEntity>() {
        override fun areItemsTheSame(oldItem:ThemesEntity , newItem: ThemesEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ThemesEntity, newItem: ThemesEntity): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeHolder {
        val view =
            ItemThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThemeHolder(view, onclickThemeListener)
    }

    override fun onBindViewHolder(holder: ThemeHolder, position: Int) {
        holder.bind(getItem(position))
    }
}