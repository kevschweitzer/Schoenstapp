package schoenstatt.schoenstapp.capitals.capital

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_single_capital.*
import org.koin.android.scope.currentScope
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import schoenstatt.schoenstapp.Constants.Companion.SINGLE_CAPITAL
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.new.CapitalProfile
import schoenstatt.schoenstapp.isOnline


class SingleCapitalActivity : AppCompatActivity() {

    private val presenter: SingleCapitalPresenter by currentScope.inject()
    private val disposables = CompositeDisposable()

    companion object {
        fun getIntent(context: Context, capital: CapitalProfile): Intent {
            val intent = Intent(context, SingleCapitalActivity::class.java)
            intent.putExtra(SINGLE_CAPITAL, capital)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_capital)
        presenter.handleIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        tv_capitales_num.text = presenter.getCapitals().toString()
    }

    fun onAddClicked(view: View) {
        val disposable = isOnline(this).subscribe({
            if(it) {
                setCapitalsTemp()
            } else Toast.makeText(this, "Internet connection problem", Toast.LENGTH_SHORT).show()
        },{it.printStackTrace()})
        disposables.add(disposable)
        //setCapitalsTemp()
    }

    private fun setCapitalsTemp() {
        presenter.tempCapitals += 1
        tv_capitals_temp.text = "+  ${presenter.tempCapitals}"
        animateAndAddTemp()
    }

    private fun animateAndAddTemp() {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator() //and this
        fadeOut.startOffset = 500
        fadeOut.duration = 500
        val animation = AnimationSet(false) //change to false
        animation.addAnimation(fadeOut)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                tv_capitals_temp.setText("")
                val disposable = presenter.addCapitals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe ({
                            tv_capitales_num.text = it.toString()
                        }, {it.printStackTrace()})
                disposables.add(disposable)

            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        tv_capitals_temp.animation = animation
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
