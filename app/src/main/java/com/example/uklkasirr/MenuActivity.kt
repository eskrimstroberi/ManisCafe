package com.example.uklkasirr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklkasirr.data.entity.Menu

class MenuActivity : AppCompatActivity() {
    private lateinit var namaMenu: EditText
    private lateinit var deskripsi: EditText
    private lateinit var harga: EditText
    private lateinit var btnSaveMenu: Button
    private lateinit var database: AppDatabaseMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        namaMenu = findViewById(R.id.namaMenu)
        deskripsi = findViewById(R.id.deskripsi)
        harga = findViewById(R.id.harga)
        btnSaveMenu = findViewById(R.id.btn_savemenu)

        database = AppDatabaseMenu.getInstance(applicationContext)

        var intent = intent.extras
        if (intent!=null) {
            val id = intent.getInt("id", 0)
            val user = database.MenuDao().get(id)

            namaMenu.setText(user.namaMenu)
            deskripsi.setText(user.deskripsi)
            harga.setText(user.harga)


        }

        btnSaveMenu.setOnClickListener {
            if (namaMenu.text.isNotEmpty() && deskripsi.text.isNotEmpty() && harga.text.isNotEmpty()) {
                if(intent!=null) {
                    //edit data
                    database.MenuDao().update(
                        Menu(
                            intent.getInt("id",0),
                            namaMenu.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString()
                        )
                    )
                } else {
                    database.MenuDao().insertAll(
                        Menu(
                            null,
                            namaMenu.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString()
                        )

                    )
                }
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua data dengan valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}