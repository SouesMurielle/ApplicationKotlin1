package com.soues.projet2

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soues.projet2.model.EleveBean
import com.squareup.picasso.Picasso

class EleveAdapter(val data: ArrayList<EleveBean>, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<EleveAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvNom = v.findViewById<TextView>(R.id.tvNom)
        val tvPrenom = v.findViewById<TextView>(R.id.tvPrenom)
        val iv = v.findViewById<ImageView>(R.id.iv)
        val root = v.findViewById<View>(R.id.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ligne_eleve, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val datum = data[position]

        vh.tvNom.text = datum.nom
        vh.tvPrenom.text = datum.prenom

        Picasso.get().load(datum.url).error(R.mipmap.image_delete_forever).placeholder(R.mipmap.image_done).into(vh.iv)

        vh.root.setOnClickListener(OnClickListener {
            onItemClickListener?.onItemClick(datum)
        })
        vh.root.setOnLongClickListener(OnLongClickListener {
            onItemClickListener?.onItemLongClick(datum, position)
            true
        })
    }

    override fun getItemCount() = data.size

    interface OnItemClickListener {
        fun onItemClick(eleveBean: EleveBean)
        fun onItemLongClick(eleveBean: EleveBean, position: Int)
    }


}




