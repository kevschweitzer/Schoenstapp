package schoenstatt.schoenstapp.capitals.new

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_new_capital.*
import org.koin.android.ext.android.inject
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.sha512
import schoenstatt.schoenstapp.databinding.FragmentNewCapitalBinding


class NewCapitalFragment: DialogFragment() {

    var newCapitalProfile: LiveData<CapitalProfile> = MutableLiveData()
    private val viewModel: NewCapitalViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentNewCapitalBinding>(inflater, R.layout.fragment_new_capital, parent, false)
        return binding.root
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