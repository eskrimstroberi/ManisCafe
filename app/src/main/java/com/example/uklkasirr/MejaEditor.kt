package com.example.uklkasirr

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uklkasirr.adapter.MejAdapter
import com.example.uklkasirr.data.entity.Meja
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MejaEditor : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf <Meja> ()
    private lateinit var adapter: MejAdapter
    private lateinit var database: AppDatabaseMeja

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja_editor)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.addmeja)

        database = AppDatabaseMeja.getInstance(applicationContext)
        adapter = MejAdapter(list)
        adapter.setDialog(object  : MejAdapter.Dialog{

            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MejaEditor)
                dialog.setTitle(list[position].namaMeja)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog, which ->
                    if (which==0){
                        //tambah
                        val intent = Intent(this@MejaEditor, MejaActivity::class.java)
                        intent.putExtra("id", list[position].mjid)
                        startActivity(intent)
                    } else if (which==1){
                        //hapus
                        database.MejaDao().delete(list[position])
                        getData()
                    } else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }


        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))


        fab.setOnClickListener{
            startActivity(Intent(this, MejaActivity::class.java))
        }

    }
    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.MejaDao().getAll())
        adapter.notifyDataSetChanged()
    }
}

