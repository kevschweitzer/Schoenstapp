package schoenstatt.schoenstapp.capitals.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_capital.view.*
import schoenstatt.schoenstapp.R
import schoenstatt.schoenstapp.capitals.new.CapitalProfile

class CapitalsAdapter(private val capitalsList: List<CapitalProfile>): RecyclerView.Adapter<CapitalsAdapter.CapitalHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapitalHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CapitalHolder(inflater.inflate(R.layout.item_capital, parent, false))
    }

    override fun getItemCount() = capitalsList.size


    override fun onBindViewHolder(holder: CapitalHolder, position: Int) {
        holder.bind(capitalsList[position])
    }

    class CapitalHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(capitalProfile: CapitalProfile) {
            view.capital_name.text = capitalProfile.name
            view.capitals_quantity.text = capitalProfile.capitals.toString()
        }
    }
}