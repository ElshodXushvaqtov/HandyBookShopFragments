package com.example.handybook.romanlarRV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.Barchasi.MyAdapterBarchasi
import com.example.handybook.R

class MyAdapterBook(
    private val arrBook: ArrayList<RomanlarData>, var context: Context,
    private var myInterface: MyInterface = object : MyInterface {
        override fun onItemTap(book: RomanlarData) {}
    }
) :
    RecyclerView.Adapter<MyAdapterBook.MyHolder>() {
    class MyHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val bookName: TextView = itemView.findViewById(R.id.book_name)
        val bookAuthor: TextView = itemView.findViewById(R.id.book_author)
        val bookImg: ImageView = itemView.findViewById(R.id.bookImg)
        val bookRating: TextView = itemView.findViewById(R.id.bookRating)

    }

    interface MyInterface {
        fun onItemTap(book: RomanlarData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.romanlar_item, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrBook.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val book = arrBook[position]
        holder.bookName.text = book.bookName
        holder.bookAuthor.text = book.bookAuthor
        holder.bookImg.setImageResource(book.bookImg)
        holder.bookRating.text = book.bookRating
        holder.itemView.setOnClickListener {
            myInterface.onItemTap(book)
        }
    }


}