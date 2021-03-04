package com.example.booklist.model

import com.example.booklist.model.VolumeInfo
import com.google.gson.annotations.SerializedName

data class Books(

       @SerializedName("items")
       val items: ArrayList<Items>,



)
