package uz.gita.bookmoliya.ui.screen.impl

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gita.bookmoliya.R
import uz.gita.bookmoliya.model.impl.ThemeModelImpl
import uz.gita.bookmoliya.presenter.ThemePresenter
import uz.gita.bookmoliya.presenter.impl.ThemePresenterImpl
import uz.gita.bookmoliya.databinding.ActivityThemeBinding
import uz.gita.bookmoliya.ui.screen.ThemeView

class ThemeActivity : AppCompatActivity(), ThemeView {
    private lateinit var binding: ActivityThemeBinding
    private val presenter: ThemePresenter = ThemePresenterImpl(ThemeModelImpl())
    private var idCategory = 0
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idCategory = intent.getIntExtra("id_", 0)
        id = intent.getIntExtra("id", 1)
        presenter.attachView(this)
        initView()
    }

    private fun initView() {
        presenter.joinTheme(id)
        Log.d("111", "initView: ")
        binding.apply {

            imgBtnPicture.setOnClickListener {
                presenter.onTransferPicture(idCategory)
            }
            bottomNavigationTheme.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_chapter -> {
                        presenter.onTransferChapter(idCategory)
                    }
                    R.id.action_settings -> {
                        presenter.onSetting()
                    }
                }
                true
            }
        }
    }


    override fun transferPicture(idCategory: Int) {
        val intent = Intent(this, ChapterPicturesActivity::class.java)
        intent.putExtra("id", idCategory)
        startActivity(intent)
    }

    override fun transferChapter(idCategory: Int) {
        val intent = Intent(this, ChapterActivity::class.java)
        intent.putExtra("id", idCategory)
        startActivity(intent)
        finish()
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun showSettings() {
        val dialog=BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_bottomsheet_settings, null, false)
        val imgBtnClose= view.findViewById<ImageButton>(R.id.img_btn_close_bottom_sheet)
        val btnDec=view.findViewById<Button>(R.id.btn_decrement)
        val btnInc=view.findViewById<Button>(R.id.btn_increment)
        val switch=view.findViewById<Switch>(R.id.switch_app_mode)

        imgBtnClose.setOnClickListener {
            dialog.dismiss()
        }
        btnDec.setOnClickListener {
            btnDec.isEnabled = !presenter.onDecrementTextSize()
            btnInc.isEnabled=true
        }
        btnInc.setOnClickListener {
            btnInc.isEnabled=!presenter.onIncrementTextSize()
            btnDec.isEnabled=true
        }
        if(presenter.isNightState){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            switch.isChecked=true
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            switch.isChecked=false
        }
        switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                presenter.isNightState=true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                presenter.isNightState=false
            }
        }
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00FFFFFF")))
        dialog.show()
    }

    override fun initTheme(name: String, text: String) {
        binding.tvThemeName.text=name
        binding.tvText.text=text
    }

    override fun setTextSize(textSize: Int) {
        binding.tvText.textSize=textSize.toFloat()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.onTransferChapter(idCategory)
    }
}