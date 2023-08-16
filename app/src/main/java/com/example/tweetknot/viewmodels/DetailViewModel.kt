package com.example.tweetknot.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetknot.models.TweetListItem
import com.example.tweetknot.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository : TweetRepository,
    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val tweets : StateFlow<List<TweetListItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "motivation"
            repository.getTweets(category)
        }
    }
}