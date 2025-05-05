package com.example.studentmanager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import androidx.appcompat.app.AlertDialog
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
private val launcher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    if (it.resultCode == Activity.RESULT_OK) {
        val stt = it.data?.getIntExtra("stt", -1)
        val name = it.data?.getStringExtra("nameUpdate")
        val id = it.data?.getStringExtra("idUpdate")
        val email = it.data?.getStringExtra("emailUpdate")
        val phone = it.data?.getStringExtra("phoneUpdate")

        val student = StudentModel(name!!, id!!, email!!, phone!!)
        list.removeAt(stt!!)
        list.add(stt, student)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show()

    } else {
        Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show()
    }
}
    override fun onContextItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.action_update -> {
                    // Handle edit action
                    val name = list[item.groupId].name
                    val id = list[item.groupId].MSSV
                    val email = list[item.groupId].email
                    val phone = list[item.groupId].phone

                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("stt", item.groupId)
                    intent.putExtra("name", name)
                    intent.putExtra("id", id)
                    intent.putExtra("email", email)
                    intent.putExtra("phone", phone)
                    launcher2.launch(intent)
                    return true
                }
                R.id.action_delete -> {
                    // Handle delete action
                    val build = AlertDialog.Builder(this)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete ${list[item.groupId].name}?")
                        .setPositiveButton("Delete"){_, _ ->
                            list.removeAt(item.groupId)
                            adapter.notifyDataSetChanged()
                        }
                        .setNegativeButton("Cancel"){
                            dialog, _ -> dialog.dismiss()
                        }
                        .setCancelable(false)

                    val dialog = build.create()
                    dialog.show()
                    return true
                }
                R.id.action_call -> {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${list[item.groupId].phone}"))
                    startActivity(intent)
                    return true
                }
                R.id.action_email -> {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${list[item.groupId].email}"))
                    startActivity(intent)
                    return true
                }
                else -> return super.onContextItemSelected(item)
            }

    }
}