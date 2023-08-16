package com.example.tweetknot.api

import com.example.tweetknot.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetAPI {
    @GET("/v3/b/64ce82779d312622a38c6dd4?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>

    @GET("/v3/b/64ce82779d312622a38c6dd4?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories() : Response<List<String>>

}