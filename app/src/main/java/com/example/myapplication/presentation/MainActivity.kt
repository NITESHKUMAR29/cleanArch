package com.example.myapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
import com.example.myapplication.core.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val newsListViewModel: NewsListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                newsListViewModel.newsListState.collect { state ->

                    when(state){
                        is UiState.Success -> {
                            Log.d("NewsListViewModel", "Success: ${state.data}")

                        }
                        is UiState.Error -> {
                            Log.d("NewsListViewModel", "Error: ${state.message}")

                        }
                        is UiState.Loading -> {
                            Log.d("NewsListViewModel", "Loading")

                        }

                        else -> {}
                    }

                }
            }
        }


    }


}