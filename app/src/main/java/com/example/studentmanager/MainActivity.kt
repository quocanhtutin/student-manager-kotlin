package com.example.studentmanager

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val list = mutableListOf<Item>()
        list.add(Item("Nguyen Quoc Anh", "20225251"))

        val adapter = List1Adapter(list)
        val l = findViewById<RecyclerView>(R.id.listV)
        l.adapter = adapter
        l.layoutManager = LinearLayoutManager(this)

        val add = findViewById<Button>(R.id.buttonAdd)
        add.setOnClickListener {
            val name = findViewById<TextView>(R.id.editName)
            val mssv = findViewById<TextView>(R.id.editMssv)
            if(name.text==""||mssv.text=="") {
                Toast.makeText(this, "Fill all", Toast.LENGTH_SHORT).show()
            }
            else{
                list.addFirst(Item(name.text.toString(), mssv.text.toString()))
                adapter.notifyItemInserted(0)
            }
        }
    }
}