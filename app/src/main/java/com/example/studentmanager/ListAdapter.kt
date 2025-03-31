package com.example.studentmanager

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

data class Item(val name: String, val MSSV: String)

class ListAdapter(val list: MutableList<Item>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val row: View = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)

        val name = row.findViewById<TextView>(R.id.txtName)
        val mssv = row.findViewById<TextView>(R.id.txtMSSV)
        val delete = row.findViewById<ImageView>(R.id.imageView)

        name.text = list[position].name
        mssv.text = list[position].MSSV
        delete.setOnClickListener {
            list.removeAt(position)
            notifyDataSetChanged()
        }

        return row
    }

}