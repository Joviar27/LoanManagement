package com.example.core.domain.usecase

import com.example.core.Result
import com.example.core.domain.model.Loan
import com.example.core.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface GetLoanUseCase {
    fun getUserLoanList(sortType: SortType?): Flow<Result<List<Loan>>>
}