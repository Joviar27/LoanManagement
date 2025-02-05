package com.example.core.di

import com.example.core.data.LoanRepository
import com.example.core.data.remote.network.ApiConfig
import com.example.core.data.remote.network.ApiService

object Injection {

    private fun provideApiService(): ApiService{
        return ApiConfig.getApiService()
    }

    fun provideRepository(): LoanRepository{
        return LoanRepository.getInstance(provideApiService())
    }
}