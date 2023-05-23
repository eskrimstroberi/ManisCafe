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
import com.example.uklkasirr.adapter.UserAdapter
import com.example.uklkasirr.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserEditor : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf <User> ()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabaseUser

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editor_user)
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.adduser)

        database = AppDatabaseUser.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object  : UserAdapter.Dialog{

            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@UserEditor)
                dialog.setTitle(list[position].fullName)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog, which ->
                    if (which==0){
                        //tambah
                        val intent = Intent(this@UserEditor, UserActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if (which==1){
                        //hapus
                        database.UserDao().delete(list[position])
                        getData()
                    } else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }


        })

        recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = adapter


        fab.setOnClickListener{
                startActivity(Intent(this, UserActivity::class.java))

        }

    }
    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.UserDao().getAll())
        adapter.notifyDataSetChanged()
    }
}