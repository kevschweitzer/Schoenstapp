package schoenstatt.schoenstapp.capitals.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_capital.view.*
import org.koin.android.scope.currentScope
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class CapitalsAdapter(private val callback: AddCapitalInterface,
                      private val capitalsList: List<CapitalProfile>): RecyclerView.Adapter<CapitalsAdapter.CapitalHolder>()  {

    interface AddCapitalInterface {
        fun addCapital(id: String): Observable<Int>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapitalHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CapitalHolder(callback, inflater.inflate(R.layout.item_capital, parent, false))
    }

    override fun getItemCount() = capitalsList.size


    override fun onBindViewHolder(holder: CapitalHolder, position: Int) {
        holder.bind(capitalsList[position])
    }

    class CapitalHolder(val callback: AddCapitalInterface,
                        val view: View): RecyclerView.ViewHolder(view) {

        fun bind(capitalProfile: CapitalProfile) {
            view.capital_name.text = capitalProfile.name
            view.capitals_quantity.text = capitalProfile.capitals.toString()
            //view.capital_id.text = capitalProfile.id
            view.add_capital_button.setOnClickListener {
                callback.addCapital(capitalProfile.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{
                            view.capitals_quantity.text = it.toString()
                        }
            }
        }
    }
}