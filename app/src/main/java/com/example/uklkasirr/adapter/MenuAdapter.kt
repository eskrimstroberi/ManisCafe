package com.example.uklkasirr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklkasirr.data.entity.Menu
import com.example.uklkasirr.R;

class MenuAdapter (var list: List<Menu>) : RecyclerView.Adapter<MenuAdapter.viewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog{
        fun onClick(position: Int)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view){
        var namaMenu: TextView
        var deskripsi: TextView
        var harga: TextView

        init {
            namaMenu = view.findViewById(R.id.namaMenu)
            deskripsi = view.findViewById(R.id.deskripsi)
            harga = view.findViewById(R.id.harga)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_menu, parent, false )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.namaMenu.text = list[position].namaMenu
        holder.deskripsi.text = list[position].deskripsi
        holder.harga.text = list[position].harga
    }

    override fun getItemCount(): Int {
        return list.size

    }
}