package com.zawhtetnaing.healthcare.app.assignment.zhn.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_pod_empty.view.*

class EmptyViewPod @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(emptyImageResource: Int, emptyMessage: String) {
        tv_empty_message.text = emptyMessage
        iv_empty_image.setImageResource(emptyImageResource)
    }

    fun setEmptyData(emptyImageUrl: String, emptyMessage: String) {
        tv_empty_message.text = emptyMessage
        Glide.with(context)
                .load(emptyImageUrl)
                .into(iv_empty_image)
    }
}