package schoenstatt.schoenstapp.capitals

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