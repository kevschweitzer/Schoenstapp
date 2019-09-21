package schoenstatt.schoenstapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.item_dialog.view.*
import java.security.MessageDigest
import kotlin.experimental.and

fun sha512(s: String): String {
    val md = MessageDigest.getInstance("SHA-512")
    val digest = md.digest(s.toByteArray())
    val sb = StringBuilder()
    for (i in digest.indices) {
        sb.append(Integer.toString((digest[i] and 0xff.toByte()) + 0x100, 16).substring(1))
    }
    return sb.toString()
}

fun exitApp(context: Context) {
    //Exit app
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_HOME)
    context.startActivity(intent)
}

fun getDialog(context: Context?, title: String, description: String)
        : AlertDialog.Builder? {
    context?.let{
        val dialog = AlertDialog.Builder(it)
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            dialog.setTitle(title)
                    .setMessage(description)
        } else {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.item_dialog, null)
            view.title.text = title
            view.description.text = description
            dialog.setView(view)
        }
        //dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
    return null
}