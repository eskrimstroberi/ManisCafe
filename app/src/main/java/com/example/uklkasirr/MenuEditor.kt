package com.example.uklkasirr

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uklkasirr.adapter.MenuAdapter
import com.example.uklkasirr.data.entity.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuEditor : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf <Menu> ()
    private lateinit var adapter: MenuAdapter
    private lateinit var database: AppDatabaseMenu

    @SuppressLint("MissingInflatedId", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_editor)
        recyclerView = findViewById(R.id.recycler_view2)
        fab = findViewById(R.id.addmenu)

        database = AppDatabaseMenu.getInstance(applicationContext)
        adapter = MenuAdapter(list)
        adapter.setDialog(object : MenuAdapter.Dialog {

            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MenuEditor)
                dialog.setTitle(list[position].namaMenu)
                dialog.setItems(
                    R.array.items_option,
                    DialogInterface.OnClickListener { dialog, which ->
                        if (which == 0) {
                            //tambah
                            val intent = Intent(this@MenuEditor, MenuActivity::class.java)
                            intent.putExtra("id", list[position].mnid)
                            startActivity(intent)
                        } else if (which == 1) {
                            //hapus
                            database.MenuDao().delete(list[position])
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

        fab.setOnClickListener{ startActivity(Intent(this,MenuActivity::class.java))


        }
    }
    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.MenuDao().getAll())
        adapter.notifyDataSetChanged()
    }
}