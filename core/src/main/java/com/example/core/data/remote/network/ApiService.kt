package com.example.core.data.remote.network

import com.example.core.data.remote.response.LoanListResponseItem
import retrofit2.http.GET

interface ApiService {

    @GET("/andreascandle/p2p_json_test/main/api/json/loans.json")
    suspend fun getLoanList(): List<LoanListResponseItem>
}