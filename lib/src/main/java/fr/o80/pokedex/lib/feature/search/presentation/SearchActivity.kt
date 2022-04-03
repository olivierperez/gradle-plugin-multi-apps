package fr.o80.pokedex.lib.feature.search.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import fr.o80.pokedex.lib.R
import fr.o80.pokedex.lib.feature.list.presentation.HomeActivity
import fr.o80.pokedex.lib.feature.about.presentation.AboutActivity

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_content, SearchFragment.newInstance())
                commit()
            }
        }

        findViewById<BottomNavigationView>(R.id.bottom_nav).apply {
            selectedItemId = R.id.search_field
            setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.home -> startActivity(HomeActivity.newIntent(context))
                    R.id.about -> startActivity(AboutActivity.newIntent(context))
                }
                false
            }
        }
    }

    override fun onBackPressed() {
        startActivity(HomeActivity.newIntent(this))
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }
}
