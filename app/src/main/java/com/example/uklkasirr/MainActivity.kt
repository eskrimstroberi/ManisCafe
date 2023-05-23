package com.example.uklkasirr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//

        val btnUser = findViewById<Button>(R.id.btnUser)
        btnUser.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, UserEditor::class.java)
            startActivity(moveIntent)
        }
        val btnMenu: Button = findViewById(R.id.btnMenu)
        btnMenu.setOnClickListener {
            val moveIntent2 = Intent(this@MainActivity, MenuEditor::class.java)
            startActivity(moveIntent2)
        }
        val btnMeja: Button = findViewById(R.id.btnMeja)
        btnMeja.setOnClickListener {
             val moveIntent3 = Intent(this@MainActivity, MejaEditor::class.java)
             startActivity(moveIntent3)

        }
    }
}
