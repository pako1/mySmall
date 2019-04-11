package com.example.mysmall

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysmall.models.Pokemon
import kotlinx.android.synthetic.main.single_poke_layout.view.*

class PokeAdapter : RecyclerView.Adapter<PokeAdapter.ViewHolder>() {

    var list = emptyList<Pokemon>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(currentView: ViewGroup, p1: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(currentView.context).inflate(R.layout.single_poke_layout, currentView, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPokemon = list[position]
        holder.bind(currentPokemon)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            itemView.pokeText.text = pokemon.name
        }
    }
}