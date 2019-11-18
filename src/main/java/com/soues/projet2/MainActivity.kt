package com.soues.projet2

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val MENU_ID_TIMEPICKER = 24
const val MENU_ID_DATEPICKER = 25
const val MENU_ID_ALERTDIALOG = 26

const val MENU_ID_CAPTURE = 4
const val MENU_ID_SERVICE = 5
const val MENU_ID_NOTIFICATION = 6
const val MENU_ID_CB = 7
const val MENU_ID_RV = 8
const val MENU_ID_BT = 9
const val MENU_ID_WEB = 10
const val MENU_ID_CP = 11

class MainActivity : AppCompatActivity(), View.OnClickListener, TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    val calendar = Calendar.getInstance()
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btValider.setOnClickListener(this)
        btAnnuler.setOnClickListener(this)

        ivDone.visibility = View.GONE
        ivDeleteForever.visibility = View.GONE

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_ID_TIMEPICKER, 0, "TimePicker")
        menu.add(0, MENU_ID_DATEPICKER, 0, "DatePicker")
        menu.add(0, MENU_ID_ALERTDIALOG, 0, "AlertDialog")
        // menu.add(0, MENU_ID_CAPTURE, 0, "CaptureVideo")
        menu.add(0, MENU_ID_SERVICE, 0, "Service Exemple")
//        menu.add(0, MENU_ID_NOTIFICATION, 0, "Notification Exemple")
//        menu.add(0, MENU_ID_CB, 0, "Code bar")
        menu.add(0, MENU_ID_RV, 0, "RecyclerView Exemple")
//        menu.add(0, MENU_ID_BT, 0, "Bluetooth")
        menu.add(0, MENU_ID_WEB, 0, "Web Service Exemple")
//        menu.add(0, MENU_ID_CP, 0, "Codepostal")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ID_TIMEPICKER -> {
                val tp = TimePickerDialog(
                    this,
                    this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                )
                tp.show()
            }
            MENU_ID_DATEPICKER -> {
                val dp = DatePickerDialog(
                    this,
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                dp.show()
            }
            MENU_ID_ALERTDIALOG -> {
                //Préparation de la fenêtre
                val alertDialogBuilder = AlertDialog.Builder(this)
                //Message
                alertDialogBuilder.setMessage("Mon message")
                //titre
                alertDialogBuilder.setTitle("Mon titre")
                //bouton ok
                alertDialogBuilder.setPositiveButton("ok") { dialog, which ->
                    //Affiche un toast apres le click sur le bouton ok
                    Toast.makeText(this, "Click sur ok", Toast.LENGTH_SHORT).show()
                }
                //Icone
                alertDialogBuilder.setIcon(R.mipmap.ic_launcher)
                //Afficher la fenêtre
                alertDialogBuilder.show()
            }
            MENU_ID_SERVICE -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                )
                    startActivity(Intent(this, ServiceExActivity::class.java))
                else
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
            }
            MENU_ID_RV -> startActivity(Intent(this, RecyclerViewExActivity::class.java))
//            MENU_ID_WEB -> startActivity(Intent(this, WebServiceActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivity(Intent(this, ServiceExActivity::class.java))
        } else {
            Toast.makeText(this, "Il faut la permission", Toast.LENGTH_SHORT).show()
        }
    }

    /* ---------------------------------
    // Callback timepicker et du datepicker
    // -------------------------------- */

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        Toast.makeText(this, FORMAT_FULL.format(calendar.time), Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        calendar.set(Calendar.DAY_OF_YEAR, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        Toast.makeText(this, FORMAT_FULL.format(calendar.time), Toast.LENGTH_SHORT).show()
    }

    /* ---------------------------------
    // Clic bouton
    // -------------------------------- */

    override fun onClick(v: View?) {

        when (v) {
            btValider -> {
                log("J'ai cliqué sur le Valider")
                if (rbJaime.isChecked) {
                    etNom.setText(rbJaime.text)
                    ivAndroid.visibility = View.GONE
                    ivDone.visibility = View.VISIBLE
                    ivDeleteForever.visibility = View.GONE
                    i = 1
                } else if (rbJaimePas.isChecked) {
                    etNom.setText(rbJaimePas.text)
                    ivAndroid.visibility = View.GONE
                    ivDone.visibility = View.VISIBLE
                    ivDeleteForever.visibility = View.GONE
                    i = 1
                } else
                    Toast.makeText(this, "Veuillez choisir entre J'aime ou J'aime pas", Toast.LENGTH_SHORT).show()
            }
            btAnnuler -> {
                if (i > 0) {
                    log("J'ai cliqué sur le bouton Annuler")
                    etNom.setText("")
                    rbGroupe.clearCheck()
                    ivAndroid.visibility = View.GONE
                    ivDone.visibility = View.GONE
                    ivDeleteForever.visibility = View.VISIBLE
                    i = 0
                } else
                    Toast.makeText(this, "Veuillez d'abord Ajouter", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Appelé lors d'un clic sur le bouton next référencé dans le XML
    fun onBtNextClick(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        //Pour passer un paramètre
        intent.putExtra(EXTRA_CLE, etNom.text.toString())
        startActivity(intent)
    }
}
