package com.soues.projet2

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soues.projet2.model.Fields

class FieldAdapter(val fields: ArrayList<Fields>, val onFieldsListener: OnFieldsListener) : RecyclerView.Adapter<FieldAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvTitre = v.findViewById<TextView>(R.id.tvTitre)
        val tvDescription = v.findViewById<TextView>(R.id.tvDescription)
        val root = v.findViewById<View>(R.id.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ligne_event, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val field = fields[position]

        vh.tvTitre.text = field.commune
        vh.tvDescription.text = field.detail

        vh.root.setOnClickListener(OnClickListener { onFieldsListener.onClick(field) })
    }

    override fun getItemCount(): Int = fields.size


    interface OnFieldsListener {
        fun onClick(fields: Fields)
    }

}