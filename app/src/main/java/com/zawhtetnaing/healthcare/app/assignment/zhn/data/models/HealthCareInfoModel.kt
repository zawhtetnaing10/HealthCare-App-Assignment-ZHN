package com.zawhtetnaing.healthcare.app.assignment.zhn.data.models

import com.zawhtetnaing.healthcare.app.assignment.zhn.data.vos.HealthCareInfoVO
import com.zawhtetnaing.healthcare.app.assignment.zhn.events.SuccessGetArticlesEvent
import com.zawhtetnaing.healthcare.app.assignment.zhn.network.HealthCareDataAgent
import com.zawhtetnaing.healthcare.app.assignment.zhn.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HealthCareInfoModel private constructor() {

    private var map: HashMap<Long, HealthCareInfoVO> = HashMap()

    init {
        EventBus.getDefault().register(this)
    }

    companion object {

        private const val DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916"

        private var objInstance: HealthCareInfoModel? = null
        fun getInstance(): HealthCareInfoModel {
            if (objInstance == null) objInstance = HealthCareInfoModel()

            val i = objInstance
            return i!!
        }
    }

    fun loadArticles() {
        RetrofitDataAgentImpl.getInstance().loadArticles(DUMMY_ACCESS_TOKEN)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetArticles(event: SuccessGetArticlesEvent) {
        event.healthCareInfo.forEach { map[it.id] = it }
    }
}