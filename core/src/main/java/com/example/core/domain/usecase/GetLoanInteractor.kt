package com.example.core.domain.usecase

import com.example.core.Result
import com.example.core.data.LoanRepository
import com.example.core.data.remote.network.ApiService
import com.example.core.domain.model.Loan
import com.example.core.domain.model.SortType
import com.example.core.domain.repository.ILoanRepository
import kotlinx.coroutines.flow.Flow

class GetLoanInteractor(
    private val loanRepository: ILoanRepository
): GetLoanUseCase {

    companion object {
        @Volatile
        private var instance: GetLoanUseCase? = null

        fun getInstance(loanRepository: ILoanRepository): GetLoanUseCase {
            return instance ?: synchronized(this) {
                instance ?: GetLoanInteractor(loanRepository)
                    .also { instance = it }
            }
        }
    }

    override fun getUserLoanList(sortType: SortType?): Flow<Result<List<Loan>>> {
        return loanRepository.getUserLoanList(sortType)
    }
}