package com.radityarin.gamelogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.radityarin.gamelogue.databinding.ItemGameBinding
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.utils.exts.addReleaseDate

class GameAdapter(
    val onGameClick: ((Game) -> Unit)? = null,
) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val items = mutableListOf<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(
            ItemGameBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val model = items[position]
        holder.bind(model)
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<Game>) {
        items.clear()
        items.addAll(data)
        refreshList()
    }

    private fun refreshList() {
        notifyDataSetChanged()
    }

    inner class GameViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Game) {
            with(binding) {
                Glide.with(this.root)
                    .load(model.backgroundImage)
                    .into(ivGame)
                tvTitle.text = model.name
                tvReleaseDate.text = model.released.addReleaseDate()
                tvRating.text = model.rating.toString()
                binding.root.setOnClickListener {
                    onGameClick?.invoke(model)
                }
            }
        }
    }

}