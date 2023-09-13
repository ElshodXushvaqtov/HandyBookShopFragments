package com.example.handybook.Barchasi

import java.io.Serializable

data class BarchasiData(
    var name: String,
    var img: Int,
    var author: String,
    var price: String,
    var rating: String
):Serializable
