package com.soues.projet2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.util.*

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        if (intent.action == Intent.ACTION_LOCALE_CHANGED) {

            val nouvelleLangue = Locale.getDefault().displayLanguage

            log("Changement de langue : $nouvelleLangue")
            Toast.makeText(context, "Changement de langue : $nouvelleLangue", Toast.LENGTH_SHORT).show()
        }
    }
}
