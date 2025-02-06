package com.example.core.di

import com.example.core.data.LoanRepository
import com.example.core.data.remote.network.ApiConfig
import com.example.core.data.remote.network.ApiService
import com.example.core.domain.repository.ILoanRepository
import com.example.core.domain.usecase.GetLoanInteractor
import com.example.core.domain.usecase.GetLoanUseCase

object Injection {

    private fun provideApiService(): ApiService{
        return ApiConfig.getApiService()
    }

    private fun provideRepository(): ILoanRepository{
        return LoanRepository.getInstance(provideApiService())
    }

    fun provideGetLoanUseCase(): GetLoanUseCase{
        return GetLoanInteractor.getInstance(provideRepository())
    }
}