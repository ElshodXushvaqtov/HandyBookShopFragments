package com.example.handybook.Barchasi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.R
import com.example.handybook.module.Book

class MyAdapterBarchasi(
    private val barchasiArray: ArrayList<BarchasiData>, var context: Context,
    private var myInterface: MyInterface = object : MyInterface {
        override fun onItemTap(book: BarchasiData) {}
    }
) :
    RecyclerView.Adapter<MyAdapterBarchasi.MyHolder>() {

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.barchasi_name)
        var img: ImageView = view.findViewById(R.id.barchasi_img)
        var author: TextView = view.findViewById(R.id.barchasi_author)
        var rating: TextView = view.findViewById(R.id.rating_barchasi)
        var price: TextView = view.findViewById(R.id.rating_barchasi)

    }

    interface MyInterface {
        fun onItemTap(book: BarchasiData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.barchasi_item, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return barchasiArray.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var barchasi_arr = barchasiArray[position]
        holder.name.text = barchasi_arr.name
        holder.img.setImageResource(barchasi_arr.img)
        holder.price.text = barchasi_arr.price
        holder.author.text = barchasi_arr.author
        holder.rating.text = barchasi_arr.rating

        holder.itemView.setOnClickListener {
            myInterface.onItemTap(barchasi_arr)
        }
    }


}