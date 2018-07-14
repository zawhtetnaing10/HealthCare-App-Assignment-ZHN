@file:Suppress("DEPRECATION")

package com.zawhtetnaing.healthcare.app.assignment.zhn.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.zawhtetnaing.healthcare.app.assignment.zhn.R
import com.zawhtetnaing.healthcare.app.assignment.zhn.data.vos.HealthCareInfoVO
import com.zawhtetnaing.healthcare.app.assignment.zhn.utils.HealthCareGlideApp
import kotlinx.android.synthetic.main.view_holder_healthcare_article.view.*

class HealthCareArticleViewHolder(itemView: View?) : BaseViewHolder(itemView) {

    init {
        itemView?.btn_like?.setOnClickListener {

            val iconCondition = itemView.iv_like_icon.drawable.constantState
            val notLiked = itemView.resources.getDrawable(R.drawable.ic_like_gray_12dp).constantState
            var likeCount = Integer.parseInt(itemView.tv_like_numbers.text.toString())

            if (iconCondition == notLiked) {
                itemView.iv_like_icon.setImageResource(R.drawable.ic_liked_primary_12dp)
                likeCount++
                itemView.tv_like_numbers.setTextColor(itemView.resources.getColor(R.color.colorPrimary))
            } else {
                itemView.iv_like_icon.setImageResource(R.drawable.ic_like_gray_12dp)
                likeCount--
                itemView.tv_like_numbers.setTextColor(itemView.resources.getColor(R.color.text_color_gray))
            }

            itemView.tv_like_numbers.text = likeCount.toString()
        }
    }

    override fun bindData(healthCareArticle: HealthCareInfoVO) {
        Glide.with(itemView!!.iv_article_image.context)
                .load(healthCareArticle.image)
                .into(itemView!!.iv_article_image)

        itemView.tv_article_title.text = healthCareArticle.title
    }

}
