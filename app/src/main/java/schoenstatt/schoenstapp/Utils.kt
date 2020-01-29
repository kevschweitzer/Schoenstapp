package schoenstatt.schoenstapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_dialog.view.*
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
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

fun isOnline(context: Context?): Single<Boolean> {
    return Single.fromCallable {
        try {
            // Connect to Google DNS to check for connection
            val timeoutMs = 2500
            val socket = Socket()
            val address = InetAddress.getByName("www.google.com")

            val socketAddress = InetSocketAddress(address, 443)

            socket.connect(socketAddress, timeoutMs)
            socket.close()

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}


