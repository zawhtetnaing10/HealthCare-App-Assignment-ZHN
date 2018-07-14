package com.zawhtetnaing.healthcare.app.assignment.zhn.data.vos

import com.google.gson.annotations.SerializedName

class AuthorVO(
        @SerializedName("author-id") val authorId: Long = 0,
        @SerializedName("author-name") val authorName: String = "",
        @SerializedName("author-picture") val authorPicture: String = ""
)