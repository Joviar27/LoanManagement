package com.example.core.data

import android.util.Log
import com.example.core.Result
import com.example.core.data.remote.network.ApiService
import com.example.core.data.remote.response.mapToModel
import com.example.core.domain.Loan
import com.example.core.domain.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoanRepository(
    private val apiService: ApiService
) {

    companion object {
        @Volatile
        private var instance: LoanRepository? = null

        fun getInstance(apiService: ApiService): LoanRepository {
            return instance ?: synchronized(this) {
                instance ?: LoanRepository(apiService)
                    .also { instance = it }
            }
        }
    }

    fun getUserLoanList(sortType: SortType?): Flow<Result<List<Loan>>> = flow {
        emit(Result.Loading)
        try{
            val response = apiService.getLoanList()
            val loanList = response.mapToModel()
            if(sortType!=null){
                val sortedLoan = when(sortType){
                    SortType.AMOUNT -> loanList.sortedBy { it.amount }
                    SortType.TERM -> loanList.sortedBy { it.term }
                    SortType.RISK -> loanList.sortedBy { it.riskRating }
                    SortType.PURPOSE -> loanList.sortedBy { it.purpose }
                }
                emit(Result.Success(sortedLoan))
            } else emit(Result.Success(loanList))
        }catch (e: Exception){
            emit(Result.Error(e.message.toString()))
            Log.e("LoanRepository", "getUserLoanList: ${e.message.toString()}")
        }
    }.flowOn(Dispatchers.IO)
}