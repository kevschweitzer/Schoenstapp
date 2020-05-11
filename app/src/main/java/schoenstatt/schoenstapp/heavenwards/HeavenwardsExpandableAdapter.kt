package schoenstatt.schoenstapp.heavenwards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import kotlinx.android.synthetic.main.item_heavenwards_prayer.view.*
import kotlinx.android.synthetic.main.item_heavenwards_title.view.*
import schoenstatt.schoenstapp.R


/**
 * @param prayers
 * Map of String (Main titles) and a List of Pairs with <SecondaryTitle, SecondaryTitleResourceId>
 */
class HeavenwardsExpandableAdapter(private val context: Context,
                                   private val prayers: Map<String, Map<String, CharSequence>>): BaseExpandableListAdapter() {

    private val mainTitlesList = prayers.keys.toList()

    override fun getGroup(groupPosition: Int) = prayers.keys.elementAt(groupPosition)

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

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

    override fun getChild(groupPosition: Int, childPosition: Int): CharSequence? {
        return prayers[mainTitlesList[groupPosition]]?.let {
            it[it.keys.elementAt(childPosition)]
        }
    }

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val children = prayers[prayers.keys.elementAt(groupPosition)]
        val layoutInflater = LayoutInflater.from(parent?.context)
        var groupView = layoutInflater.inflate(R.layout.item_heavenwards_prayer, parent,false)

        if(children?.size == 1){ //Show directly the prayer
            groupView?.prayerView?.text = children.values.first()
        } else {
            //Show children in expandableList
            children?.let {
                val secondLevelELV = SecondLevelExpandableListView(context)
                secondLevelELV.setAdapter(HeavenwardsChildrenExpandableAdapter(children))
                secondLevelELV.setGroupIndicator(null)
                groupView = secondLevelELV
            }
        }

        return groupView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun getGroupCount() = prayers.size

}


class SecondLevelExpandableListView(context: Context?) : ExpandableListView(context) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
        //var heightMeasureSpec = heightMeasureSpec
        val height = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, height)
    }
}