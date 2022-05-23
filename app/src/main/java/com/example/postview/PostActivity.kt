package com.example.postview

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.getJsonDataFromAsset

class PostActivity : AppCompatActivity(), OnPostClickListener {

    private lateinit var postRecycler: RecyclerView
    private lateinit var commentaryRecycler: RecyclerView
    private lateinit var postId: TextView
    private lateinit var api: ApiService
    private lateinit var postAdapter: PostsAdapter
    private lateinit var perfis: List<Perfil>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        postRecycler = findViewById(R.id.post_recycler)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)

        api.fetAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        val jsonFileString = getJsonDataFromAsset(this, "perfis.json")
        val listPersonType = object : TypeToken<List<Perfil>>() {}.type
        val gson = Gson()

        perfis = gson.fromJson(jsonFileString, listPersonType)

    }

    private fun showData(posts: List<Post>) {
        postAdapter = PostsAdapter(posts, this@PostActivity)

        postRecycler.layoutManager = LinearLayoutManager(this@PostActivity)
        postRecycler.adapter = postAdapter

    }

    override fun onPostItemClicked(position: Int) {
        chamarAlert(position)
    }

    fun chamarAlert(position: Int) {

        val view = View.inflate(this@PostActivity, R.layout.layout_comentario, null)
        val builder = AlertDialog.Builder(this@PostActivity)
        builder.setView(view)

        postId = view.findViewById(R.id.post_id)
        val dialog = builder.create()

        val post = postAdapter.getItem(position)

        postId.text = post.id.toString()

        api.fetAllCommentary(post.id).enqueue(object : Callback<List<Comentario>> {
            override fun onResponse(call: Call<List<Comentario>>, response: Response<List<Comentario>>) {
                val comentarios = response.body()!!
                commentaryRecycler = view.findViewById(R.id.comentario_recycler)

                commentaryRecycler.apply {
                    layoutManager = LinearLayoutManager(this@PostActivity)
                    adapter = ComentarioAdapter(comentarios, this@PostActivity, perfis)
                }
            }

            override fun onFailure(call: Call<List<Comentario>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}