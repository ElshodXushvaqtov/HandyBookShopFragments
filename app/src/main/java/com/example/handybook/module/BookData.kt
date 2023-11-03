package com.example.handybook.module

data class BookData(

    var id: Int,
    var name: String,
    var typeId: Int,
    var file: String,
    var year: Int,
    var author: String,
    var status: Int,
    var rating: Int,
    var description: String,
    var image: String,
    var lang: String,
    var countPage: Int,
    var publisher: String

)