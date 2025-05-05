package com.example.studentmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Detail Screen"
        val stt = intent.getIntExtra("stt", -1)
        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")

        val nameUpdate = findViewById<EditText>(R.id.edtName)
        val idUpdate = findViewById<EditText>(R.id.edtID)
        val emailUpdate = findViewById<EditText>(R.id.edtEmail)
        val phoneUpdate = findViewById<EditText>(R.id.edtPhone)

        nameUpdate.setText(name)
        idUpdate.setText(id)
        emailUpdate.setText(email)
        phoneUpdate.setText(phone)


        val updateButton = findViewById<Button>(R.id.btnUpdate)
        updateButton.setOnClickListener {
            val nameEdit = nameUpdate.text.toString().trim()
            val idEdit = idUpdate.text.toString().trim()
            val emailEdit = emailUpdate.text.toString().trim()
            val phoneEdit = phoneUpdate.text.toString().trim()

            if (nameEdit.isEmpty() || idEdit.isEmpty() || emailEdit.isEmpty() || phoneEdit.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("stt", stt)
                intent.putExtra("nameUpdate", nameEdit)
                intent.putExtra("idUpdate", idEdit)
                intent.putExtra("emailUpdate", emailEdit)
                intent.putExtra("phoneUpdate", phoneEdit)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}