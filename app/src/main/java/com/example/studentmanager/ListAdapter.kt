package com.example.studentmanager

import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.models.StudentModel


class List1Adapter(val list: MutableList<StudentModel>, val activity: MainActivity): RecyclerView.Adapter<List1Adapter.List1ViewHolder>() {
    inner class List1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnCreateContextMenuListener {
        val name = itemView.findViewById<TextView>(R.id.txtName)
        val mssv = itemView.findViewById<TextView>(R.id.txtMSSV)

        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menuInfo.apply {
                menu?.add(adapterPosition, R.id.action_update, Menu.NONE, "Update")
                menu?.add(adapterPosition, R.id.action_delete, Menu.NONE, "Delete")
                menu?.add(adapterPosition, R.id.action_call, Menu.NONE, "Call")
                menu?.add(adapterPosition, R.id.action_email, Menu.NONE, "Send email")
            }
        }

    }

//        private fun onContextMenuItemClick(item: () -> Unit): Boolean {
//            val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//        when (item.itemId) {
//            R.id.action_update -> { Log.v("TAG", "Update: ${info.position}") }
//            R.id.action_delete -> { Log.v("TAG", "Delete: ${info.position}") }
//            R.id.action_call -> { Log.v("TAG", "Call: ${info.position}") }
//            R.id.action_email -> { Log.v("TAG", "Email: ${info.position}") }
//        }
//        return true
//        }
//        }
//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        activity.menuInflater.inflate(R.menu.centext_menu, menu)
//        super.onCreateContextMenu(menu, v, menuInfo)
//    }
//
//    fun onContextItemSelected(item: MenuItem): Boolean {
//        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//        when (item.itemId) {
//            R.id.action_update -> { Log.v("TAG", "Update: ${info.position}") }
//            R.id.action_delete -> { Log.v("TAG", "Delete: ${info.position}") }
//            R.id.action_call -> { Log.v("TAG", "Call: ${info.position}") }
//            R.id.action_email -> { Log.v("TAG", "Email: ${info.position}") }
//        }
//        return true
//    }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): List1Adapter.List1ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return List1ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: List1Adapter.List1ViewHolder, position: Int) {
            holder.name.text = list[position].name
            holder.mssv.text = list[position].MSSV
        }

        override fun getItemCount(): Int = list.size


    }
