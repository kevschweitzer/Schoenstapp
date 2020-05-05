package schoenstatt.schoenstapp.heavenwards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListView.OnGroupExpandListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_heavenwards.*
import schoenstatt.schoenstapp.R


class HeavenwardsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, HeavenwardsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavenwards)

        val index = resources.getStringArray(R.array.heavenwards_index)
        val prayers = generatePrayerList()
        val prayersMap = index.zip(prayers).toMap()
        expandableHeavenwardsIndex.apply {
            setAdapter(HeavenwardsExpandableAdapter(context, prayersMap))
            setOnGroupExpandListener(object : OnGroupExpandListener {
                var previousGroup = -1
                override fun onGroupExpand(groupPosition: Int) {
                    if (groupPosition != previousGroup) expandableHeavenwardsIndex.collapseGroup(previousGroup)
                    previousGroup = groupPosition
                }
            })
        }
    }

    private fun generatePrayerList(): List<Map<String, CharSequence>> {
        return mutableListOf<Map<String, CharSequence>>().apply {
            addAll(
                listOf(
                        getDataMapWith(null, R.array.morning_consecration),
                        getDataMapWith(R.array.instrument_mass_titles, R.array.instrument_mass_texts),
                        getDataMapWith(R.array.schoenstatt_office, R.array.schoenstatt_office_texts),
                        getDataMapWith(null, R.array.angelus),
                        getDataMapWith(R.array.way_of_the_cross_titles, R.array.way_of_the_cross_children),
                        getDataMapWith(R.array.rosary, R.array.rosary_texts),
                        getDataMapWith(null, R.array.evening_consecration),
                        getDataMapWith(null, R.array.accept_o_lord),
                        getDataMapWith(null, R.array.beg_all_cross),
                        getDataMapWith(null, R.array.take_this_child),
                        getDataMapWith(null, R.array.look_our_family),
                        getDataMapWith(null, R.array.hold_the_scepter),
                        getDataMapWith(null, R.array.in_time_of_need),
                        getDataMapWith(R.array.prayer_of_leaders_titles, R.array.prayer_of_leaders_texts),
                        getDataMapWith(null, R.array.shepherds_prayer),
                        getDataMapWith(null, R.array.male_vocations),
                        getDataMapWith(null, R.array.prayer_international),
                        getDataMapWith(null, R.array.in_pressing_need),
                        getDataMapWith(null, R.array.thanks_for_everything),
                        getDataMapWith(null, R.array.i_praise_you_mother),
                        getDataMapWith(null, R.array.right_hand_wither),
                        getDataMapWith(null, R.array.ring_prayer),
                        getDataMapWith(null, R.array.home_song),
                        getDataMapWith(null, R.array.hymn_instruments),
                        getDataMapWith(null, R.array.hymn_thanksgiving),
                        getDataMapWith(null, R.array.other_prayers),
                        getDataMapWith(null, R.array.my_queen_my_mother),
                        getDataMapWith(null, R.array.confidence),
                        getDataMapWith(null, R.array.providentia_divina),
                        getDataMapWith(null, R.array.with_heartfelt_love),
                        getDataMapWith(null, R.array.walk_like_you),
                        getDataMapWith(null, R.array.morning_offering),
                        getDataMapWith(null, R.array.puritatis_tuae_causa),
                        getDataMapWith(null, R.array.mother_thrice_admirable),
                        getDataMapWith(null, R.array.in_your_holy_heart),
                        getDataMapWith(null, R.array.adsum),
                        getDataMapWith(null, R.array.accept_my_freedom),
                        getDataMapWith(null, R.array.to_holy_spirit),
                        getDataMapWith(null, R.array.o_holy_spirit),
                        getDataMapWith(null, R.array.exorcism_prayer),
                        getDataMapWith(null, R.array.additional_home_song),
                        getDataMapWith(null, R.array.short_prayers),
                        getDataMapWith(null, R.array.home_shrine_prayer),
                        getDataMapWith(null, R.array.triune_god)
                )
            )
        }
    }

    private fun getDataMapWith(titlesId: Int?, textsId: Int): Map<String, CharSequence> {
        val titles = titlesId?.let { resources.getStringArray(it) } ?: arrayOf("")
        val texts = resources.getTextArray(textsId)
        return titles.zip(texts).toMap()
    }
}
