import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.goiz.pokedex.Abilities
import com.goiz.pokedex.Evolution
import com.goiz.pokedex.Stats

@Suppress("DEPRECATION")

internal class TabAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int
) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Stats()
            }
            1 -> {
                Evolution()
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