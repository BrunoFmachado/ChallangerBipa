package com.example.challangerbipa.api

import com.example.challangerbipa.data.model.Node
import retrofit2.http.GET

interface LightningApiService {
    @GET("lightning/nodes/rankings/connectivity")
    suspend fun getTopNodes(): List<Node>
}
