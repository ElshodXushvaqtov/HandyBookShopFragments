package com.example.handybook.BookType

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.R
import com.example.handybook.searchFragment.SearchAdapter

class MyAdapterType(
    private val typeList: ArrayList<BookTypeData>,
    var context: Context,
    private var myInterface: MyInterface = object : MyInterface {
        override fun onItemTap(bookType: BookTypeData) {

        }
    }
) :
    RecyclerView.Adapter<MyAdapterType.MyHolder>() {

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

    interface MyInterface {
        fun onItemTap(bookType: BookTypeData)

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val type = typeList[position]
        holder.bookType.text = type.typeName
        holder.itemView.setOnClickListener {
            myInterface.onItemTap(type)
        }

    }

}