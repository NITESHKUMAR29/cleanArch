package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.utils.UiState
import com.example.myapplication.domain.model.Article
import com.example.myapplication.domain.model.Resource
import com.example.myapplication.domain.useCase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _newsListState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val newsListState: StateFlow<UiState<List<Article>>> = _newsListState

    init {
        getNews()
    }

    private fun getNews(category: String = "business") {
        viewModelScope.launch {
           getTopHeadlinesUseCase.invoke(category).collect {
               when(it){
                   is Resource.Success -> {
                       _newsListState.value = UiState.Success(it.data)
                   }
                   is Resource.Error -> {
                       _newsListState.value = UiState.Error(it.message)
                   }
                   is Resource.Loading -> {
                       _newsListState.value = UiState.Loading
                   }

               }
           }
        }
    }
}
