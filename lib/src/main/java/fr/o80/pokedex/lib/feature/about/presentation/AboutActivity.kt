package fr.o80.pokedex.lib.feature.about.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.feature.list.presentation.HomeActivity
import fr.o80.pokedex.lib.feature.search.presentation.SearchActivity
import fr.o80.pokedex.lib.util.LinkSpan

@AndroidEntryPoint
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        findViewById<BottomNavigationView>(R.id.bottom_nav).apply {
            selectedItemId = R.id.about
            setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.home -> startActivity(HomeActivity.newIntent(context))
                    R.id.search_field -> startActivity(SearchActivity.newIntent(context))
                }
                false
            }
        }

        findViewById<TextView>(R.id.content).apply {
            text = buildAboutContent()
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun buildAboutContent(): CharSequence = buildSpannedString {
        append("Everything is fine, this application will soon go prod!\n")

        append("\nAuthor:\n")
        bold {
            append("Olivier Perez\n")
            inSpans(LinkSpan(this@AboutActivity, "https://github.com/olivierperez")) {
                append("https://github.com/olivierperez\n")
            }
            inSpans(LinkSpan(this@AboutActivity, "https://www.twitch.tv/GNU_Coding_Cafe")) {
                append("https://www.twitch.tv/GNU_Coding_Cafe\n")
            }
        }

        append("\nWith images from:\n")
        bold {
            inSpans(LinkSpan(this@AboutActivity, "https://www.iconfinder.com/Gui_Lhem")) {
                append("Gui_Lhem\n")
            }
            inSpans(LinkSpan(this@AboutActivity, "https://www.iconfinder.com/Ayub_Irawan")) {
                append("Ayub_Irawan\n")
            }
        }
    }

    override fun onBackPressed() {
        startActivity(HomeActivity.newIntent(this))
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }
}
