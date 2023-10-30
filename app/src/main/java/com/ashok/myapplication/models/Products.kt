package com.ashok.myapplication.models

data class Products(
    val data:List<Data>
){
    data class Data(
        val id:Int,
        val name:String,
        val color:String,
        val pantone_value:String,

    )
}

