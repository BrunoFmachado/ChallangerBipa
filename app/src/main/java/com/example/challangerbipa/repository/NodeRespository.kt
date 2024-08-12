package com.example.challangerbipa.repository

import com.example.challangerbipa.api.LightningApiService
import com.example.challangerbipa.data.model.Node
import javax.inject.Inject

class NodeRepository @Inject constructor(
    private val apiService: LightningApiService
) {
    suspend fun fetchTopNodes(): List<Node> {
        return apiService.getTopNodes()
    }
}
