import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.goiz.pokedex.Abilities
import com.goiz.pokedex.Evolution
import com.goiz.pokedex.Stats
import me.sargunvohra.lib.pokekotlin.model.Pokemon

@Suppress("DEPRECATION")

internal class TabAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int,
        var pokemonId: Bundle?
) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                val stats = Stats()
                stats.arguments = pokemonId
                return stats
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