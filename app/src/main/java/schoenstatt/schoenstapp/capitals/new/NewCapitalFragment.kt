package schoenstatt.schoenstapp.capitals.new

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_new_capital.*
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.sha512


class NewCapitalFragment: DialogFragment() {

    var newCapitalProfile: LiveData<CapitalProfile> = MutableLiveData()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_capital, parent, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_new_capital_submit.setOnClickListener{
            val passwordMD5 = sha512(et_new_capital_password.text.toString())
            (newCapitalProfile as MutableLiveData).value = CapitalProfile(et_new_capital_name.text.toString(), passwordMD5)
            dismiss()
        }
    }
}