package com.example.core.remote.retrofit

import com.example.core.remote.response.LoanListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/andreascandle/p2p_json_test/refs/heads/main/api/json/loans.json")
    suspend fun getLoanList(): LoanListResponse
}