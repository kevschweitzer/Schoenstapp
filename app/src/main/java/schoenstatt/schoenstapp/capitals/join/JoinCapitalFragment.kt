package schoenstatt.schoenstapp.capitals.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_join_capital.*
import schoenstatt.schoenstapp.R

class JoinCapitalFragment(val callback: JoinCapitalInterface, var capitalId: String?): DialogFragment() {


    interface JoinCapitalInterface {
        fun joinCapital(link: String)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_join_capital, parent, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_join_capital.setOnClickListener{
            callback.joinCapital(et_join_capital_link.text.toString())
            dismiss()
        }
        if(capitalId!=null) et_join_capital_link.setText(capitalId)
    }
}