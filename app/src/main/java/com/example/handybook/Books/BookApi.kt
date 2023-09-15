package com.example.handybook.Books

import android.content.Context
import com.example.handybook.Darsliklar.DarsliklarData
import com.example.handybook.R
import com.example.handybook.romanlarRV.RomanlarData
import com.google.gson.Gson

class BookApi(context:Context) {

    companion object {
        private var instance: BookApi? = null
        fun newInstance(context: Context): BookApi {
            if (instance == null) {
                instance = BookApi(context)
            }
            return instance!!
        }
    }

    private val shared = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    private val gson = Gson()

     fun dataBooks() {

        var arrRoman = ArrayList<RomanlarData>()
        arrRoman.add(
            RomanlarData(
                "O'tkan Kunlar",
                R.drawable.otkankunlar,
                "Abdulla Qodiriy",
                "5.0"
            )
        )
        arrRoman.add(
            RomanlarData(
                "Ikki Eshik Orasi",
                R.drawable.ikkieshik_orasi,
                "O'tkir Hoshimov",
                "4.9"
            )
        )
        arrRoman.add(
            RomanlarData(
                "Urush Tugasa",
                R.drawable.urush_tugasa,
                "Qo'chqor Norqobilov",
                "4.8"
            )
        )
        arrRoman.add(
            RomanlarData(
                "O'tkan Kunlar",
                R.drawable.otkankunlar,
                "Abdulla Qodiriy",
                "5.0"
            )
        )
        arrRoman.add(
            RomanlarData(
                "Ikki Eshik Orasi",
                R.drawable.ikkieshik_orasi,
                "O'tkir Hoshimov",
                "4.9"
            )
        )
        arrRoman.add(
            RomanlarData(
                "Urush Tugasa",
                R.drawable.urush_tugasa,
                "Qo'chqor Norqobilov",
                "4.8"
            )
        )

        val booksJson = gson.toJson(arrRoman)
        shared.edit().putString("romanlar", booksJson).apply()
    }

     fun dataDarsliklar() {

       var darsliklarArray = ArrayList<DarsliklarData>()

        darsliklarArray.add(DarsliklarData(R.drawable.algebra,"Algebra","Davlat"))
        darsliklarArray.add(DarsliklarData(R.drawable.fizika,"Fizika","Nyuton"))
        darsliklarArray.add(DarsliklarData(R.drawable.ona_tili,"Ona tili","Navoiy"))
         darsliklarArray.add(DarsliklarData(R.drawable.algebra,"Algebra","Davlat"))
        darsliklarArray.add(DarsliklarData(R.drawable.fizika,"Fizika","Nyuton"))
        darsliklarArray.add(DarsliklarData(R.drawable.ona_tili,"Ona tili","Mavoiy"))


         val booksJson = gson.toJson(darsliklarArray)
         shared.edit().putString("darsliklar", booksJson).apply()

    }

}