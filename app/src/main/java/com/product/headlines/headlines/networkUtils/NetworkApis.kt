package com.product.headlines.headlines.networkUtils

import com.product.headlines.headlines.models.request.HeadLineRequest
import com.product.headlines.headlines.models.response.HeadlineResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkApis {

    @GET("/v2/top-headlines")
    suspend fun getHeadLine(@QueryMap mHeadLineRequest: Map<String,String>): HeadlineResponse
}