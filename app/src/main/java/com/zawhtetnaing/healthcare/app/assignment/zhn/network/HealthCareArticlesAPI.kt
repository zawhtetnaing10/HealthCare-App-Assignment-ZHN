package com.zawhtetnaing.healthcare.app.assignment.zhn.network

import com.zawhtetnaing.healthcare.app.assignment.zhn.network.responses.GetHealthCareArticlesResponse
import com.zawhtetnaing.healthcare.app.assignment.zhn.utils.HealthCareConstants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HealthCareArticlesAPI {

    @FormUrlEncoded
    @POST(HealthCareConstants.GET_HEALTH_CARE_INFO)
    fun loadArticles(@Field(HealthCareConstants.PARAM_ACCESS_TOKEN) accessToken: String): Call<GetHealthCareArticlesResponse>
}