package com.example.studentmanager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.models.StudentModel

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")

    private lateinit var list: MutableList<StudentModel>
    private lateinit var adapter: List1Adapter

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Main Screen"
        list = mutableListOf()
        list.add(StudentModel("Qa", "123", "aaa", "aaa"))
        adapter = List1Adapter(list, this )
        val l = findViewById<RecyclerView>(R.id.listV)
        l.adapter = adapter
        l.layoutManager = LinearLayoutManager(this)
//        l.setOnCreateContextMenuListener(this)
//        registerForContextMenu(l)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add -> {
                openAddStudent()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openAddStudent() {
        val intent = Intent(this, AddStudentActivity::class.java)
        launcher.launch(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val name = it.data?.getStringExtra("name")
            val id = it.data?.getStringExtra("id")
            val email = it.data?.getStringExtra("email")
            val phone = it.data?.getStringExtra("phone")

            val student = StudentModel(name!!, id!!, email!!, phone!!)
            list.addFirst(student)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        menu?.add(Menu.NONE, R.id.action_update, Menu.NONE, "Update")
//        menuInflater.inflate(R.menu.centext_menu, menu)
//        super.onCreateContextMenu(menu, v, menuInfo)
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        val info = item.menuInfo
//        if (info is AdapterView.AdapterContextMenuInfo) {
//            // It's an AdapterView, and we have the info we need
//            val position = info.position
//            val id = info.id
//            // ... do something with position and id ...
//            when (item.itemId) {
//                R.id.action_update -> {
//                    // Handle edit action
//                    Log.d("TAG", "onContextItemSelected: $position")
//                    return true
//                }
//                R.id.action_delete -> {
//                    // Handle delete action
//                    Log.d("TAG", "onContextItemSelected: $position")
//
//                    return true
//                }
//                R.id.action_call -> {
//                    Log.d("TAG", "onContextItemSelected: $position")
//
//                    return true
//                }
//                R.id.action_email -> {
//                    Log.d("TAG", "onContextItemSelected: $position")
//
//                    return true
//                }
//                else -> return super.onContextItemSelected(item)
//            }
//        } else {
//            Log.d("TAG", "onContextItemSelected: $item")
//            return super.onContextItemSelected(item)
//        }
//    }
}