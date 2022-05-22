package com.example.postview

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts")
    fun fetAllPosts(): Call<List<Post>>

    @GET("/posts/{postId}/comments")
    fun fetAllCommentary(@Path("postId") id: Int): Call<List<Comentario>>
}