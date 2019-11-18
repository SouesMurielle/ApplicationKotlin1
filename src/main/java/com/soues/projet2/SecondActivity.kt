package com.soues.projet2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

const val EXTRA_CLE = "MaCle"

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var valeur = intent.getStringExtra(EXTRA_CLE)
        if (valeur.toString().trim().isEmpty()) {
            valeur = "vide"
        }
        bt.text = valeur

        Toast.makeText(this, valeur, Toast.LENGTH_SHORT).show()
    }
}
