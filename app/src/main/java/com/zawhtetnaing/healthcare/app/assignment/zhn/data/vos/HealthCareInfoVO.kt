package com.zawhtetnaing.healthcare.app.assignment.zhn.data.vos

import com.google.gson.annotations.SerializedName

class HealthCareInfoVO(
        @SerializedName("id") val id: Long = 0,
        @SerializedName("title") val title: String = "",
        @SerializedName("image") val image: String = "",
        @SerializedName("author") val author: AuthorVO? = null,
        @SerializedName("short-description") val shortDescription: String = "",
        @SerializedName("published-date") val publishedDate: String = "",
        @SerializedName("complete-url") val completeUrl: String = ""
)
