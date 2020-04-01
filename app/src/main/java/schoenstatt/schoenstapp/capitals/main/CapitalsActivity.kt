package schoenstatt.schoenstapp.capitals.main

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_capitals.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.*
import schoenstatt.schoenstapp.Constants.Companion.BASE_SHARE_URL
import schoenstatt.schoenstapp.capitals.capital.SingleCapitalActivity
import schoenstatt.schoenstapp.capitals.join.JoinCapitalFragment
import schoenstatt.schoenstapp.capitals.new.CapitalProfile
import schoenstatt.schoenstapp.capitals.new.NewCapitalFragment
import schoenstatt.schoenstapp.home.MainActivity

class CapitalsActivity : AppCompatActivity(), CapitalsAdapter.CapitalAdapterInterface, JoinCapitalFragment.JoinCapitalInterface {

    private var isMenuOpen: Boolean = false
    private val presenter: CapitalsPresenter by currentScope.inject()

    companion object {
        fun getIntent(context: Context) = Intent(context, CapitalsActivity::class.java)

        fun getIntent(context: Context, appData: Uri): Intent {
            val intent = Intent(context, CapitalsActivity::class.java)
            intent.putExtra(Constants.APPLINK_DATA, appData)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capitals)
        handleIntent()
    }

    private fun handleIntent() {
        val appLinkData = intent.getParcelableExtra<Uri>(Constants.APPLINK_DATA)
        if(appLinkData!=null) {
            val joinCapitalLink = appLinkData.toString()
            joinCapitalFromAppLink(joinCapitalLink)
        }
    }

    override fun onResume() {
        super.onResume()
        setUpCapitals()
    }

    override fun openSingleCapital(capital: CapitalProfile) {
        startActivity(SingleCapitalActivity.getIntent(this, capital))
    }

    override fun joinCapital(link: String) {
        if(link.isNotEmpty()) {
            val id = link.split("?") [1]
            presenter.joinCapital(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        if(it) setUpCapitals()
                        else {
                            Toast.makeText(this, getString(R.string.error_cant_join), Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }

    private fun setUpCapitals() {
        capitals_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@CapitalsActivity)
            presenter.getCapitals()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        loading.visibility = View.GONE
                        adapter = CapitalsAdapter(this@CapitalsActivity,this@CapitalsActivity, it)
                    }
            invalidate()
        }
        registerForContextMenu(capitals_recycler_view)
    }

    fun newCapital(view: View){
        val newCapitalFragment = NewCapitalFragment()
        newCapitalFragment.newCapitalProfile.observe(this, Observer { newCapitalCreated(it) })
        newCapitalFragment.show(supportFragmentManager, "fragment_new_capital")
    }

    fun joinCapital(view: View?) {
        val joinCapitalFragment = JoinCapitalFragment(this, null)
        joinCapitalFragment.show(supportFragmentManager, "fragment_join_capital")
    }

    fun joinCapitalFromAppLink(capitalId: String) {
        val joinCapitalFragment = JoinCapitalFragment(this, capitalId)
        joinCapitalFragment.show(supportFragmentManager, "fragment_join_capital")
    }

    private fun newCapitalCreated(capitalProfile: CapitalProfile?) {
        capitalProfile?.let{
            presenter.createCapital(capitalProfile)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if(it) {
                            setUpCapitals()
                            Toast.makeText(this, getString(R.string.correct_create_capital), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, getString(R.string.error_create_capital), Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val adapter = capitals_recycler_view.adapter as CapitalsAdapter
        val position = adapter.adapterPosition
        when(item?.itemId) {
            R.id.ctx_menu_remove -> {
                getDialog(this, getString(R.string.delete_capital_title), getString(R.string.delete_capital_description))
                        ?.setCancelable(false)
                        ?.setNegativeButton(getString(R.string.cancel_text)){ _, _ -> /*dismiss*/}
                        ?.setPositiveButton(getString(R.string.ok_text)){ _, _ ->
                            position?.let {
                                presenter.deleteCapital(adapter.capitalsList[it].id)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe{ result ->
                                            if(result) {
                                                setUpCapitals()
                                                Toast.makeText(this, getString(R.string.correct_delete_capital), Toast.LENGTH_SHORT).show()
                                            } else {
                                                Toast.makeText(this, getString(R.string.error_delete_capital), Toast.LENGTH_SHORT).show()
                                            }
                                        }
                            }
                        }
                        ?.show()
            }
            R.id.ctx_menu_share -> {
                position?.let {
                    shareCapital(adapter.capitalsList[it])
                }
            }
            R.id.ctx_menu_exit -> {
                getDialog(this, getString(R.string.leave_capital_title), getString(R.string.leave_capital_description))
                        ?.setCancelable(false)
                        ?.setNegativeButton(getString(R.string.cancel_text)){ _, _ -> /*dismiss*/}
                        ?.setPositiveButton(getString(R.string.ok_text)) { _, _ ->
                            position?.let {
                                presenter.exitCapital(adapter.capitalsList[it].id)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe {
                                            if (it) {
                                                Toast.makeText(this, getString(R.string.correct_leave_capital), Toast.LENGTH_SHORT).show()
                                                setUpCapitals()
                                            } else {
                                                Toast.makeText(this, getString(R.string.error_leave_capital), Toast.LENGTH_SHORT).show()
                                            }
                                        }
                            }
                        }
                        ?.show()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun shareCapital(capitalProfile: CapitalProfile) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_capital_text,BASE_SHARE_URL,capitalProfile.id))

        try {
            startActivity(Intent.createChooser(intent, getString(R.string.share_title)))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.error_sharing), Toast.LENGTH_SHORT).show()
        }
    }

    fun handleMenu(view: View){
        if(isMenuOpen){
            closeMenu()
        } else {
            openMenu()
        }
    }

    private fun openMenu() {
        isMenuOpen = true
        new_capital.visibility = View.VISIBLE
        join_capital.visibility = View.VISIBLE
        new_capital.animate().translationY(-convertDpToPixel(40f, this))
        join_capital.animate().translationY(-convertDpToPixel(80f, this))
    }

    private fun closeMenu() {
        isMenuOpen = false
        new_capital.animate().translationY(0f)
        join_capital.animate().translationY(0f).withEndAction {
            new_capital.visibility = View.GONE
            join_capital.visibility = View.GONE
        }
    }


}
