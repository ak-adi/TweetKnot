package com.example.tweetknot.repository

import com.example.tweetknot.api.TweetAPI
import com.example.tweetknot.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetAPI: TweetAPI) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories : StateFlow<List<String>>
        get() = _categories

    suspend fun getTweets(category : String){
        val result = tweetAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if (result.isSuccessful && result.body() != null){
            _tweets.emit(result.body()!!)
        }
    }

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets : StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories(){
        val result = tweetAPI.getCategories()
        if (result.isSuccessful && result.body() != null){
            _categories.emit(result.body()!!)
        }
    }


}