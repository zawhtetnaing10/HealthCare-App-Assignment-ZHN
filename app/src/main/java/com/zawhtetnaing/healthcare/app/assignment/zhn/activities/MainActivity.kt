package com.zawhtetnaing.healthcare.app.assignment.zhn.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.zawhtetnaing.healthcare.app.assignment.zhn.R
import com.zawhtetnaing.healthcare.app.assignment.zhn.adapters.HealthCareArticleAdapter
import com.zawhtetnaing.healthcare.app.assignment.zhn.data.models.HealthCareInfoModel
import com.zawhtetnaing.healthcare.app.assignment.zhn.events.ApiErrorEvent
import com.zawhtetnaing.healthcare.app.assignment.zhn.events.SuccessGetArticlesEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HealthCareArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        appbar.addOnOffsetChangedListener { _, offset ->
            if (offset == 0)
                toolbar.visibility = View.INVISIBLE
            else
                toolbar.visibility = View.VISIBLE
        }

        adapter = HealthCareArticleAdapter()
        rv_healthcare_articles.adapter = adapter
        rv_healthcare_articles.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayout.VERTICAL, false)

        swipe_refresh_layout.isRefreshing = true

        swipe_refresh_layout.setOnRefreshListener {
            HealthCareInfoModel.getInstance().loadArticles()
        }

        HealthCareInfoModel.getInstance().loadArticles()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthCareArticles(event: SuccessGetArticlesEvent): Unit {
        adapter.mArticlesList = event.healthCareInfo
        swipe_refresh_layout.isRefreshing = false
        vp_empty.visibility = View.GONE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureGetHealthCareArticles(event: ApiErrorEvent): Unit {
        swipe_refresh_layout.isRefreshing = false
        vp_empty.visibility = View.VISIBLE
    }
}
