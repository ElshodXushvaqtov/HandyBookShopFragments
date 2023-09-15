package com.example.handybook.Darsliklar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.R
import com.example.handybook.romanlarRV.MyAdapterBook
import com.example.handybook.romanlarRV.RomanlarData
import java.util.ArrayList

class MyAdapterDarsliklar(
    private val darsliklarList: ArrayList<DarsliklarData>, var context: Context,
    private var myInterface: MyInterface = object :
        MyInterface {
        override fun onItemTap(book: DarsliklarData) {}
    }
) :
    RecyclerView.Adapter<MyAdapterDarsliklar.MyHolder>() {

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {

        var img: ImageView = view.findViewById(R.id.darslikImg)
        var name: TextView = view.findViewById(R.id.darslikName)
        var author: TextView = view.findViewById(R.id.darslikAuthor)

    }

    interface MyInterface {
        fun onItemTap(book: DarsliklarData)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.darsliklar_layout, parent, false)
        return MyHolder(itemview)
    }

    override fun getItemCount(): Int {
        return darsliklarList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val darslik = darsliklarList[position]
        holder.img.setImageResource(darslik.darslikImg)
        holder.name.text = darslik.darslikName
        holder.author.text = darslik.darslikAuthor
        holder.itemView.setOnClickListener {
            myInterface.onItemTap(darslik)
        }
    }

}