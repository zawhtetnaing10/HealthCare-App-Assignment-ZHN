package com.zawhtetnaing.healthcare.app.assignment.zhn.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zawhtetnaing.healthcare.app.assignment.zhn.R
import com.zawhtetnaing.healthcare.app.assignment.zhn.data.vos.HealthCareInfoVO
import com.zawhtetnaing.healthcare.app.assignment.zhn.viewholders.BaseViewHolder
import com.zawhtetnaing.healthcare.app.assignment.zhn.viewholders.CategoryViewHolder
import com.zawhtetnaing.healthcare.app.assignment.zhn.viewholders.HealthCareArticleViewHolder


class HealthCareArticleAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val CATEGORY_VIEW_TYPE = 1000
        private const val ARTICLES_VIEW_TYPE = 2000
    }

    var mArticlesList: List<HealthCareInfoVO> = ArrayList()
        set(articlesList: List<HealthCareInfoVO>) {
            field = articlesList
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == CATEGORY_VIEW_TYPE) {
            val view = inflater.inflate(R.layout.view_holder_categories, parent, false)
            CategoryViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.view_holder_healthcare_article, parent, false)
            HealthCareArticleViewHolder(view)
        }
    }

    override fun getItemCount(): Int = mArticlesList.size


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindData(mArticlesList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) CATEGORY_VIEW_TYPE else ARTICLES_VIEW_TYPE
    }
}
