package uz.gita.bookmoliya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.navigation.NavigationBarView
import uz.gita.bookmoliya.data.repository.AppRepository
import uz.gita.bookmoliya.data.repository.impl.AppRepositoryImpl
import uz.gita.bookmoliya.databinding.ActivityMainBinding
import uz.gita.bookmoliya.ui.screen.impl.HomeFragment
import uz.gita.bookmoliya.ui.screen.impl.SelectedFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository: AppRepository = AppRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
        binding.bottomNavigationMain.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment())
                        .commit()
                    return@OnItemSelectedListener true
                }
                R.id.action_favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SelectedFragment()).commit()
                    return@OnItemSelectedListener true
                }
            }
            false
        })
        checkNightModeActivated()
    }

    private fun checkNightModeActivated() {
        if (repository.isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}