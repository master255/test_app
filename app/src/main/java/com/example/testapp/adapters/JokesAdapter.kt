package com.example.testapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.`interface`.OnClickListener
import com.example.testapp.models.Joke

class JokesAdapter(
    private val jokeList: MutableList<Joke>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<JokesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSetup: TextView = itemView.findViewById(R.id.tvSetup)
        val tvPunchline: TextView = itemView.findViewById(R.id.tvPunchline)
        val cvCard: CardView = itemView.findViewById(R.id.cvCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = jokeList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = jokeList[position]
        holder.cvCard.setOnClickListener({ onClickListener.onClick(holder.cvCard, listItem) })
        holder.tvSetup.text = listItem.setup
        holder.tvPunchline.text = listItem.punchline
    }
}