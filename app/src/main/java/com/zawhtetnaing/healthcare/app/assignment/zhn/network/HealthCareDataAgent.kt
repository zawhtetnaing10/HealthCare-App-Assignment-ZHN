package com.zawhtetnaing.healthcare.app.assignment.zhn.network

interface HealthCareDataAgent {
    fun loadArticles(accessToken: String): Unit
}