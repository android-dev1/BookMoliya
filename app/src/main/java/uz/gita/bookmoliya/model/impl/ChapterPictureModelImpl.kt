package uz.gita.bookmoliya.model.impl

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import uz.gita.bookmoliya.app.App
import uz.gita.bookmoliya.data.repository.AppRepository
import uz.gita.bookmoliya.data.repository.impl.AppRepositoryImpl
import uz.gita.bookmoliya.model.ChapterPictureModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class ChapterPictureModelImpl(private val idCategory: Int) : ChapterPictureModel {
    private val repository: AppRepository = AppRepositoryImpl()
    private val fileName = repository.getCategory(idCategory).picture
    private var pdfRenderer: PdfRenderer? = null
    private var currentPage: PdfRenderer.Page? = null
    private var parcelFileDescriptor: ParcelFileDescriptor? = null

    @Throws(IOException::class)
    override fun openReader() {
        val file = File(App.instance.cacheDir, "$fileName.pdf")
        if (!file.exists()) {
            val asset: InputStream = App.instance.assets.open("$fileName.pdf")
            val output = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var size: Int
            while (asset.read(buffer).also { size = it } != -1) {
                output.write(buffer, 0, size)
            }
            asset.close()
            output.close()
        }
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        parcelFileDescriptor?.let {
            pdfRenderer = PdfRenderer(it)
        }
    }

    @Throws(IOException::class)
    override fun closeReader() {
        currentPage?.let {
            it.close()
        }
        pdfRenderer!!.close()
        parcelFileDescriptor!!.close()
    }

    override fun onPreviousDocClick():Int {
        return currentPage!!.index - 1
    }

    override fun onNextDocClick():Int {
        return currentPage!!.index + 1
    }

    override fun showPage(index: Int): Bitmap? {
        if (pdfRenderer!!.pageCount <= index) {
            return null
        }
        currentPage?.let {
            it.close()
        }
        currentPage = pdfRenderer!!.openPage(index)
        var bitmap: Bitmap
        currentPage?.let {
            bitmap = Bitmap.createBitmap(
                it.width, it.height,
                Bitmap.Config.ARGB_8888
            )
            it.render(bitmap!!, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            return bitmap
        }
        return null
    }

    override fun isEnabledPrev(): Boolean {
        val index = currentPage?.index
        return 0 != index;
    }

    override fun isEnabledNext(): Boolean {
        var index = 0
        var pageCount = 0
        currentPage?.index?.let {
            index = it
            pageCount = pdfRenderer!!.pageCount
        }
        return index + 1 < pageCount;
    }
}