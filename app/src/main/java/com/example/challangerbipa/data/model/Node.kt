package com.example.challangerbipa.data.model

data class Node(
    val publicKey: String,
    val alias: String,
    val channels: Int,
    val capacity: Long,
    val firstSeen: Long,
    val updatedAt: Long,
    val location: Location? = null
)

data class Location(
    val city: String?,
    val country: String?
)
