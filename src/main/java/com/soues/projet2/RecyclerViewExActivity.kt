package com.soues.projet2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.soues.projet2.model.EleveBean
import kotlinx.android.synthetic.main.activity_recycler_view_ex.*

class RecyclerViewExActivity : AppCompatActivity(), EleveAdapter.OnItemClickListener {

    //Données
    val data = ArrayList<EleveBean>()

    //Outils
    val adapter = EleveAdapter(data, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_ex)

        //Reglage du recyclerView
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    fun onBtAjouterClic(view: View) {

        //J'insère à la position 0
        data.add(0, EleveBean("eleve${data.size}", "eleve"))
        //J'indique que j'ai inséré à la position 0
        adapter.notifyItemInserted(0)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(eleveBean: EleveBean) {
        Toast.makeText(this, "Clic sur " + eleveBean.nom + " " + eleveBean.prenom, Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(eleveBean: EleveBean, position: Int) {
        data.remove(eleveBean)
        adapter.notifyItemRemoved(position)
    }
}
