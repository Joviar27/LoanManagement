package com.example.core.domain.repository

import com.example.core.Result
import com.example.core.domain.model.Loan
import com.example.core.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface ILoanRepository {
    fun getUserLoanList(sortType: SortType?): Flow<Result<List<Loan>>>
}