package com.zawhtetnaing.healthcare.app.assignment.zhn.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.zawhtetnaing.healthcare.app.assignment.zhn.data.vos.HealthCareInfoVO


abstract class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(healthCareArticle: HealthCareInfoVO)
}