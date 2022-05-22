package com.example.postview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PostsAdapter(private val posts: List<Post>, private val onPostClickListener : OnPostClickListener) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.post_title)
        val body: TextView = itemView.findViewById(R.id.post_body)
        val iscurtido: ImageButton = itemView.findViewById(R.id.btn_curtida)
        val qtCurtida: TextView = itemView.findViewById(R.id.quantidade_curtida)
        val qtComentario: TextView = itemView.findViewById(R.id.quantidade_comentary)
        val qtShared: TextView = itemView.findViewById(R.id.quantidade_shared)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curtiu : Int = (0..1).random()

        if(curtiu == 1){
            holder.iscurtido.setImageResource(R.drawable.ic_curtida)
        }else{
            holder.iscurtido.setImageResource(R.drawable.ic_no_curtida)
        }

        holder.title.text = posts[position].title
        holder.body.text = posts[position].body
        holder.qtCurtida.text = (0..1000).random().toString()
        holder.qtComentario.text = (0..1000).random().toString()
        holder.qtShared.text = (0..1000).random().toString()

        holder.itemView.setOnClickListener {
            onPostClickListener.onPostItemClicked(position)
        }
    }

    fun getItem(position: Int): Post {
        return posts[position]
    }

    override fun getItemCount() = posts.size


}
