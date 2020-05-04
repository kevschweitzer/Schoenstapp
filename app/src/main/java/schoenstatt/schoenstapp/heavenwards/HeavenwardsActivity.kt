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
            add(morningConsecrationMap())
            add(instrumentMassMap())
            add(schoenstattOfficeMap())
            add(afterAngelusMap())
            add(wayOfCrossMap())
            add(rosaryMap())
            add(eveningConsecrationMap())
        }
    }

    private fun morningConsecrationMap(): Map<String, CharSequence> {
        return listOf("").zip(resources.getTextArray(R.array.morning_consecration)).toMap()
    }

    private fun instrumentMassMap(): Map<String, CharSequence> {
        val titles = resources.getStringArray(R.array.instrument_mass_titles)
        val texts = resources.getTextArray(R.array.instrument_mass_texts)
        return titles.zip(texts).toMap()
    }

    private fun schoenstattOfficeMap(): Map<String, CharSequence> {
        val titles = resources.getStringArray(R.array.schoenstatt_office)
        val texts = resources.getTextArray(R.array.schoenstatt_office_texts)
        return titles.zip(texts).toMap()
    }

    private fun afterAngelusMap(): Map<String, CharSequence> {
       return listOf("").zip(resources.getTextArray(R.array.angelus)).toMap()
    }

    private fun wayOfCrossMap(): Map<String, CharSequence> {
        val titles = resources.getStringArray(R.array.way_of_the_cross_titles)
        val texts = resources.getTextArray(R.array.way_of_the_cross_children)
        return titles.zip(texts).toMap()
    }

    private fun rosaryMap(): Map<String, CharSequence> {
        val titles = resources.getStringArray(R.array.rosary)
        val texts = resources.getTextArray(R.array.rosary_texts)
        return titles.zip(texts).toMap()
    }

    private fun eveningConsecrationMap(): Map<String, CharSequence> {
        return listOf("").zip(resources.getTextArray(R.array.evening_consecration)).toMap()
    }
}
