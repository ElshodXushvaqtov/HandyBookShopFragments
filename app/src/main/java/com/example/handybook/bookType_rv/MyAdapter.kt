package com.example.handybook.bookType_rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.R

class MyAdapter(private val typeList: ArrayList<BookType>) :
    RecyclerView.Adapter<MyAdapter.MyHolder>() {

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {

        val bookType: TextView = view.findViewById(R.id.typeName_txt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.booktype_list_item, parent, false)
        return MyHolder(itemview)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val type = typeList[position]
        holder.bookType.text = type.typeName
    }

}