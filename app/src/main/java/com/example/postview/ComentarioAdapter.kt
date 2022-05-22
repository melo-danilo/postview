package com.example.postview

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.net.URL


class ComentarioAdapter(
    private val comentarios: List<Comentario>,
    private val context: Context,
    private val perfis: List<Perfil>
) : RecyclerView.Adapter<ComentarioAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val perfil: ImageView = itemView.findViewById(R.id.image_perfil)
        val name: TextView = itemView.findViewById(R.id.comentario_name)
        val email: TextView = itemView.findViewById(R.id.comentario_email)
        val body: TextView = itemView.findViewById(R.id.comentario_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get()
            .load(perfis[comentarios[position].id].url)
            .into(holder.perfil)

        holder.name.text = comentarios[position].name
        holder.email.text = comentarios[position].email
        holder.body.text = comentarios[position].body
    }

    override fun getItemCount() = comentarios.size

}