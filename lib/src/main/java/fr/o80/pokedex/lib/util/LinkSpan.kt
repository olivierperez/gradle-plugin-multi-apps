package fr.o80.pokedex.lib.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.style.ClickableSpan
import android.view.View

class LinkSpan(
    private val activity: Activity,
    private val link: String
) : ClickableSpan() {
    override fun onClick(p0: View) {
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }
}
