package com.example.paulofelipeoliveirasouza.projecttvshow.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Show(
        @SerializedName("image") val image : Image? = null,
        @SerializedName("name") val title: String = "",
        @SerializedName("genres") val genres: ArrayList<String> = arrayListOf(),
        @SerializedName("summary") val summary: String = "",
        @SerializedName("premiered") val date: Date? = null
)

data class Image(
        @SerializedName("medium") val medium: String = "",
        @SerializedName("original") val original: String = ""
)

data class Shows(
        @SerializedName("show") val show: Show? = null
)
