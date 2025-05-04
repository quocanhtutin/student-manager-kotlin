package com.example.studentmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addButton = findViewById<Button>(R.id.btnAdd)
        addButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.edtName).text.toString().trim()
            val id = findViewById<EditText>(R.id.edtID).text.toString().trim()
            val email = findViewById<EditText>(R.id.edtEmail).text.toString().trim()
            val phone = findViewById<EditText>(R.id.edtPhone).text.toString().trim()

            if(name.isEmpty() || id.isEmpty() || email.isEmpty() || phone.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("id", id)
                intent.putExtra("email", email)
                intent.putExtra("phone", phone)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}