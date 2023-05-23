package com.example.uklkasirr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklkasirr.data.entity.Meja

class MejaActivity : AppCompatActivity() {
    private lateinit var namaMeja: EditText
    private lateinit var btnSaveMeja: Button
    private lateinit var database: AppDatabaseMeja
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja)

        namaMeja = findViewById(R.id.namaMeja)

        btnSaveMeja = findViewById(R.id.btn_saveMeja)

        database = AppDatabaseMeja.getInstance(applicationContext)

        var intent = intent.extras
        if (intent!=null) {
            val id = intent.getInt("id", 0)
            val meja = database.MejaDao().get(id)

            namaMeja.setText(meja.namaMeja)

        }

        btnSaveMeja.setOnClickListener {
            if (namaMeja.text.isNotEmpty()) {
                if(intent!=null) {
                    //edit data
                    database.MejaDao().update(
                        Meja(
                            intent.getInt("id",0),
                            namaMeja.text.toString(),

                        )
                    )
                } else {
                    database.MejaDao().insertAll(
                        Meja(
                            null,
                            namaMeja.text.toString(),
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