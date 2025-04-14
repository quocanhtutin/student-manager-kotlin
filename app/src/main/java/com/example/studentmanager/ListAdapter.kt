package com.example.studentmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Item(val name: String, val MSSV: String)

class List1Adapter(val list: MutableList<Item>): RecyclerView.Adapter<List1Adapter.List1ViewHolder>() {
    inner class List1ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.txtName)
        val mssv = itemView.findViewById<TextView>(R.id.txtMSSV)
        val buttonDelete = itemView.findViewById<ImageView>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): List1Adapter.List1ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return List1ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: List1Adapter.List1ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.mssv.text = list[position].MSSV
        holder.buttonDelete.setOnClickListener {
            list.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = list.size
}