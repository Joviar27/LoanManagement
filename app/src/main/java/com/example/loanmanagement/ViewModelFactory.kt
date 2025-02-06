package com.example.loanmanagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.Injection
import com.example.core.domain.usecase.GetLoanUseCase
import com.example.loanmanagement.ui.home.HomeViewModel

class ViewModelFactory private constructor(
    private val getLoanUseCase: GetLoanUseCase
) : ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideGetLoanUseCase())
                    .also { instance = it }
            }
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(getLoanUseCase) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}