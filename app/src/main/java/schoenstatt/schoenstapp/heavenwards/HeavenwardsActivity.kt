package schoenstatt.schoenstapp.heavenwards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
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
        val prayers = resources.getTextArray(R.array.heavenwards_prayers)
        val prayersMap = index.zip(prayers).toMap()
        expandableHeavenwardsIndex.apply {
            setAdapter(HeavenwardsExpandableAdapter(prayersMap))
            setOnGroupExpandListener(object : OnGroupExpandListener {
                var previousGroup = -1
                override fun onGroupExpand(groupPosition: Int) {
                    if (groupPosition != previousGroup) expandableHeavenwardsIndex.collapseGroup(previousGroup)
                    previousGroup = groupPosition
                }
            })
        }

    }
}
