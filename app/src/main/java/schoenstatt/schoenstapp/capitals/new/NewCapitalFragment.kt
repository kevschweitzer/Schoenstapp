package schoenstatt.schoenstapp.capitals.new

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_new_capital.*
import schoenstatt.schoenstapp.R


class NewCapitalFragment: DialogFragment() {

    var newCapitalProfile: LiveData<CapitalProfile> = MutableLiveData()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_capital, parent, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_new_capital_submit.setOnClickListener{
            (newCapitalProfile as MutableLiveData).value = CapitalProfile("", et_new_capital_name.text.toString(),
                    et_new_capital_password.text.toString())
            dismiss()
        }
    }
}