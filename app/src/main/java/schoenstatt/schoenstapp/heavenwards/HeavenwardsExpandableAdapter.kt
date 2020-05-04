package schoenstatt.schoenstapp.heavenwards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import kotlinx.android.synthetic.main.item_heavenwards_prayer.view.*
import kotlinx.android.synthetic.main.item_heavenwards_title.view.*
import schoenstatt.schoenstapp.R


class HeavenwardsExpandableAdapter(private val prayers: Map<String,CharSequence>): BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int) = prayers.keys.elementAt(groupPosition)

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = false

    override fun hasStableIds() = false

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val title = prayers.keys.elementAt(groupPosition)
        val groupView = if(convertView == null ) {
            val layoutInflater = LayoutInflater.from(parent?.context)
            layoutInflater.inflate(R.layout.item_heavenwards_title, parent,false)
        } else convertView

        groupView.title.text = title

        return groupView
    }

    override fun getChildrenCount(groupPosition: Int) = 1

    override fun getChild(groupPosition: Int, childPosition: Int): String? {
        return prayers[prayers.keys.elementAt(groupPosition)].toString()
    }

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val prayer = prayers[prayers.keys.elementAt(groupPosition)]
        val groupView = if(convertView == null ) {
            val layoutInflater = LayoutInflater.from(parent?.context)
            layoutInflater.inflate(R.layout.item_heavenwards_prayer, parent,false)
        } else convertView

        groupView.description.text = prayer
        return groupView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int) = 1L

    override fun getGroupCount() = prayers.size

}