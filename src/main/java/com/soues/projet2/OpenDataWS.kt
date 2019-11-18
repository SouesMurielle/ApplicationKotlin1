package com.soues.projet2

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.soues.projet2.model.Fields
import com.soues.projet2.model.Resultat

const val WS_URL = "https://data.gers.fr/api/records/1.0/search/?dataset=inventaire-des-defibrillateurs-automatises-externes-dans-le-gers&facet=commune&facet=nature"

class OpenDataWS(private val okHttpUtils: OkHttpUtils) : AppCompatActivity() {

    fun getFieldsDuServeur(): ArrayList<Fields> {

        val reponseJson = okHttpUtils.sendGetOkHttpRequest(WS_URL)

        val gson = Gson()
        val resultat: Resultat = gson.fromJson(reponseJson, Resultat::class.java)

        var fields = ArrayList<Fields>()

        for (record in resultat.record) {
            fields.add(record.fields)
        }

        println(reponseJson)

        return fields
    }


}