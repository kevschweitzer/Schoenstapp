package schoenstatt.schoenstapp.forgot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.R

class ForgotPasswordActivity : AppCompatActivity() {

    private val presenter: ForgotPasswordPresenter by currentScope.inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, ForgotPasswordActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }

    fun onSendForgotMailClicked(view: View) {
        val mail = forgot_mail_et.text.toString()
        if(!mail.isNullOrEmpty()) {
            presenter.onSendForgotMailClicked(mail)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        if(it) {
                            Toast.makeText(this, getString(R.string.forgot_email_sent), Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, getString(R.string.error_forgot_email), Toast.LENGTH_SHORT).show()
                        }
                    }.subscribe()
        }
    }
}
