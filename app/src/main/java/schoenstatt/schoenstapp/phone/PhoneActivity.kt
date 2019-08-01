package schoenstatt.schoenstapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_phone.*
import java.io.ByteArrayOutputStream
import java.util.*


class PhoneActivity : AppCompatActivity() {

    private lateinit var pkPhrase: String

    companion object {
        const val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 9002

        fun getIntent(context: Context) = Intent(context, PhoneActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)
        checkPermissions()
        this.tv_pk_phrase.text = getPhrase()
    }


    private fun getPhrase(): String {
        val phrases = resources.getStringArray(R.array.pk_phrases)
        val r = Random()
        val phraseNumber = r.nextInt(phrases.size)

        pkPhrase = phrases[phraseNumber]
        return pkPhrase
    }

    private fun checkPermissions() {
        val permissionWriteStorage = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PhoneActivity.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
        }
    }

    fun share(view: View) {
        val bitmap = generateScreenshot()
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_TEXT, "Enviado a través de Schoenstapp");
        intent.putExtra(Intent.EXTRA_STREAM, getImageUri(bitmap))

        try {
            startActivity(Intent.createChooser(intent, "Compartir usando"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show()
        }

    }

    fun refresh(view: View){
        tv_pk_phrase.text = getPhrase()
        tv_pk_phrase.invalidate()
    }

    private fun generateScreenshot(): Bitmap {
        val view = phrase_container
        val b = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)
        view.draw(c)
        return b
    }

    fun getImageUri(inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }
}
