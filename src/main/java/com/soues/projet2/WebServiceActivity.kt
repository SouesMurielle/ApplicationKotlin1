package com.soues.projet2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.soues.projet2.model.Fields
import kotlinx.android.synthetic.main.activity_ws.*

class WebServiceActivity(openDataWS: OpenDataWS) : AppCompatActivity(), FieldAdapter.OnFieldsListener {

    var fields: ArrayList<Fields>? = null

    var adapter: FieldAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws)

        fields = ArrayList<Fields>()

        adapter = FieldAdapter(fields!!, this)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

//        tvTitre.text = fields.
    }

    override fun onClick(fields: Fields) {
        Toast.makeText(this, "Clic sur " + fields.commune, Toast.LENGTH_SHORT).show()
    }

}
