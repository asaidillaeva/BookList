package com.example.booklist.model
import com.google.gson.annotations.SerializedName

data class VolumeInfo(
    @SerializedName("title")
    val title: String,

    @SerializedName("authors")
    val authors: ArrayList<String>,

    @SerializedName("publishedDate")
    val publishedDate: String,

    @SerializedName("pageCount")
    val  pageCount: Int,

    @SerializedName("infoLink")
    val infoLink: String,

    @SerializedName("imageLinks")
    val imageLinks: Image
)
