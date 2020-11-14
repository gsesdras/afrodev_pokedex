import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.goiz.pokedex.fragments.Abilities
import com.goiz.pokedex.fragments.Evolution
import com.goiz.pokedex.fragments.Stats

@Suppress("DEPRECATION")

internal class TabAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int,
        var bundle: Bundle?
) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                val stats = Stats()
                stats.arguments = bundle
                return stats
            }
            1 -> {
                val evolution = Evolution()
                evolution.arguments = bundle
                return evolution
            }
            2 -> {
                Abilities()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}