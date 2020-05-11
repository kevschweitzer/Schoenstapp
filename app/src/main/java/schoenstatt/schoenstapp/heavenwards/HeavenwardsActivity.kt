package schoenstatt.schoenstapp.heavenwards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListView.OnGroupExpandListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_heavenwards.*
import kotlinx.android.synthetic.main.toolbar.*
import schoenstatt.schoenstapp.R


class HeavenwardsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, HeavenwardsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavenwards)
        title_text.text = getString(R.string.heavenwards_title)

        setListeners()
        setHeavenwardsPrayers()
    }

    private fun setListeners() {

    }

    private fun setHeavenwardsPrayers() {
        val index = resources.getStringArray(R.array.heavenwards_index)
        val prayers = generatePrayersList()
        val prayersMap = index.zip(prayers).toMap()
        expandableHeavenwardsIndex.apply {
            setAdapter(HeavenwardsExpandableAdapter(context, prayersMap))
            setOnGroupExpandListener(object : OnGroupExpandListener {
                var previousGroup = -1
                override fun onGroupExpand(groupPosition: Int) {
                    if (groupPosition != previousGroup) expandableHeavenwardsIndex.collapseGroup(previousGroup)
                    previousGroup = groupPosition
                    expandableHeavenwardsIndex.setSelection(groupPosition)
                }
            })
        }
    }

    private fun generatePrayersList(): List<Map<String, CharSequence>> {
        val prayersList = mutableListOf(
                getDataMapWith(textsId =  R.array.morning_consecration),
                getDataMapWith(R.array.instrument_mass_titles, R.array.instrument_mass_texts),
                getDataMapWith(R.array.schoenstatt_office, R.array.schoenstatt_office_texts),
                getDataMapWith(textsId =  R.array.angelus),
                getDataMapWith(R.array.way_of_the_cross_titles, R.array.way_of_the_cross_children),
                getDataMapWith(R.array.rosary, R.array.rosary_texts),
                getDataMapWith(textsId =  R.array.evening_consecration),
                getDataMapWith(textsId =  R.array.accept_o_lord),
                getDataMapWith(textsId =  R.array.beg_all_cross),
                getDataMapWith(textsId =  R.array.take_this_child),
                getDataMapWith(textsId =  R.array.look_our_family),
                getDataMapWith(textsId =  R.array.hold_the_scepter),
                getDataMapWith(textsId =  R.array.in_time_of_need),
                getDataMapWith(R.array.prayer_of_leaders_titles, R.array.prayer_of_leaders_texts),
                getDataMapWith(textsId =  R.array.shepherds_prayer),
                getDataMapWith(textsId =  R.array.male_vocations),
                getDataMapWith(textsId =  R.array.prayer_international),
                getDataMapWith(textsId =  R.array.in_pressing_need),
                getDataMapWith(textsId =  R.array.thanks_for_everything),
                getDataMapWith(textsId =  R.array.i_praise_you_mother),
                getDataMapWith(textsId =  R.array.right_hand_wither),
                getDataMapWith(textsId =  R.array.ring_prayer),
                getDataMapWith(textsId =  R.array.home_song),
                getDataMapWith(textsId = R.array.hymn_instruments),
                getDataMapWith(textsId =  R.array.hymn_thanksgiving)
        )
        val otherPrayersIds = resources.obtainTypedArray(R.array.other_prayers_ids)
        for (i in 0 until otherPrayersIds.length()) {
            prayersList.add(getDataMapWith(textsId = otherPrayersIds.getResourceId(i, -1)))
        }
        otherPrayersIds.recycle()

        return prayersList
    }

    private fun getDataMapWith(titlesId: Int? = null, textsId: Int): Map<String, CharSequence> {
        val titles = titlesId?.let { resources.getStringArray(it) } ?: arrayOf("")
        val texts = resources.getTextArray(textsId)
        return titles.zip(texts).toMap()
    }
}
