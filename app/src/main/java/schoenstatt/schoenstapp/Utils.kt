package schoenstatt.schoenstapp

import android.content.Context
import android.content.Intent
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