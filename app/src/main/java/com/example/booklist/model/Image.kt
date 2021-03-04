package com.example.booklist.model

import com.google.gson.annotations.SerializedName

data class Image(
        @SerializedName("thumbnail")
        val imageLink: String
)
