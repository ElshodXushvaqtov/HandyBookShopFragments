package com.example.handybook.searchFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.Darsliklar.DarsliklarData
import com.example.handybook.R

class SearchAdapter(

    private var searchArr: MutableList<DarsliklarData>,
    var context: Context,
    private var myInterface: MyInterface = object : MyInterface {
        override fun onItemTap(book: DarsliklarData) {}
    }


) : RecyclerView.Adapter<SearchAdapter.MyHolder>() {

    class MyHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var name: TextView = itemview.findViewById(R.id.search_name)
        var author: TextView = itemview.findViewById(R.id.search_author)
        var img: ImageView = itemview.findViewById(R.id.search_img)

    }

    interface MyInterface {
        fun onItemTap(book: DarsliklarData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return searchArr.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val searchItem = searchArr[position]
        holder.name.text = searchItem.darslikName
        holder.author.text = searchItem.darslikAuthor
        holder.img.setImageResource(searchItem.darslikImg)
        holder.itemView.setOnClickListener {

            myInterface.onItemTap(searchItem)

        }
    }

}