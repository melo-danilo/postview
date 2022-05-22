package com.example.postview

data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val curtida: Boolean
)