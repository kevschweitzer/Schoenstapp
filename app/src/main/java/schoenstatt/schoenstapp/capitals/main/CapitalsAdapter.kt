package schoenstatt.schoenstapp.capitals.main

import android.view.*
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
                      val capitalsList: List<CapitalProfile>): RecyclerView.Adapter<CapitalsAdapter.CapitalHolder>()  {

    var adapterPosition: Int? = null

    interface AddCapitalInterface {
        fun addCapital(id: String): Observable<Int>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapitalHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CapitalHolder(callback, inflater.inflate(R.layout.item_capital, parent, false))
    }

    override fun getItemCount() = capitalsList.size


    override fun onBindViewHolder(holder: CapitalHolder, position: Int) {
        holder.itemView.setOnLongClickListener { adapterPosition=holder.adapterPosition
        false}
        holder.bind(capitalsList[position])
    }

    class CapitalHolder(val callback: AddCapitalInterface,
                        val view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

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
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.ctx_menu_remove, Menu.NONE,"Delete")
            menu?.add(Menu.NONE, R.id.ctx_menu_share, Menu.NONE,"Share")
        }
    }
}