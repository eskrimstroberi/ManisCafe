package com.example.uklkasirr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklkasirr.R;
import com.example.uklkasirr.data.entity.Meja

class  MejAdapter(var list: MutableList<Meja>) : RecyclerView.Adapter<MejAdapter.viewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog{
        fun onClick(position: Int)
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view){
        var namaMeja: TextView

        init {
            namaMeja = view.findViewById(R.id.namaMeja)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_meja, parent, false )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.namaMeja.text = list[position].namaMeja
    }

    override fun getItemCount(): Int {
        return list.size

    }
}