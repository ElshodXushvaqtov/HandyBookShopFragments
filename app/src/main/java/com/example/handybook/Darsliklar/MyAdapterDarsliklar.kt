package com.example.handybook.Darsliklar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook.R
import java.util.ArrayList

class MyAdapterDarsliklar(private val darsliklarList: ArrayList<DarsliklarData>) :
    RecyclerView.Adapter<MyAdapterDarsliklar.MyHolder>() {

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {

        var img: ImageView = view.findViewById(R.id.darslikImg)

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
    }

}