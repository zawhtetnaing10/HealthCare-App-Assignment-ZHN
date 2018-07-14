package com.zawhtetnaing.healthcare.app.assignment.zhn.network

import com.google.gson.Gson
import com.zawhtetnaing.healthcare.app.assignment.zhn.events.ApiErrorEvent
import com.zawhtetnaing.healthcare.app.assignment.zhn.events.SuccessGetArticlesEvent
import com.zawhtetnaing.healthcare.app.assignment.zhn.network.responses.GetHealthCareArticlesResponse
import com.zawhtetnaing.healthcare.app.assignment.zhn.utils.HealthCareConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgentImpl private constructor() : HealthCareDataAgent {

    companion object {
        private var objInstance: RetrofitDataAgentImpl? = null

        fun getInstance(): RetrofitDataAgentImpl {
            if (objInstance == null) objInstance = RetrofitDataAgentImpl()

            val i = objInstance
            return i!!
        }
    }

    private var mApi: HealthCareArticlesAPI? = null

    init {

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(HealthCareConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()

        mApi = retrofit.create(HealthCareArticlesAPI::class.java)
    }

    override fun loadArticles(accessToken: String) {
        val call = mApi!!.loadArticles(accessToken)
        call.enqueue(object : Callback<GetHealthCareArticlesResponse> {
            override fun onResponse(call: Call<GetHealthCareArticlesResponse>?, response: Response<GetHealthCareArticlesResponse>?) {

                val healthCareInfoResponse: GetHealthCareArticlesResponse? = response?.body()

                if (healthCareInfoResponse != null && healthCareInfoResponse.isResponseOk()) {
                    val event = SuccessGetArticlesEvent(healthCareInfoResponse.healthCareInfo)
                    EventBus.getDefault().post(event)
                } else if (healthCareInfoResponse != null) {
                    val event = ApiErrorEvent(healthCareInfoResponse.message)
                    EventBus.getDefault().post(event)
                } else {
                    val event = ApiErrorEvent("Empty data returned from network call ")
                    EventBus.getDefault().post(event)
                }
            }

            override fun onFailure(call: Call<GetHealthCareArticlesResponse>?, t: Throwable?) {
                val event = ApiErrorEvent(t!!.message!!)
                EventBus.getDefault().post(event)
            }

        })
    }
}