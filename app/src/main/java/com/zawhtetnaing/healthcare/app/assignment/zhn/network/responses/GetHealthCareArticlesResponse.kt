package com.zawhtetnaing.healthcare.app.assignment.zhn.network.responses

import com.google.gson.annotations.SerializedName
import com.zawhtetnaing.healthcare.app.assignment.zhn.data.vos.HealthCareInfoVO

class GetHealthCareArticlesResponse(
        @SerializedName("code") val code: Int = 0,
        @SerializedName("message") val message: String = "",
        @SerializedName("healthcare-info") val healthCareInfo: List<HealthCareInfoVO> = ArrayList()
) {
    fun isResponseOk(): Boolean = code == 200 && healthCareInfo != null
}