package com.radityarin.gamelogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.radityarin.gamelogue.databinding.ItemGameBinding
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.utils.exts.addReleaseDate

class GamePaginationAdapter(
    val onGameClick: ((Game) -> Unit)? = null,
) : PagingDataAdapter<Game, GamePaginationAdapter.GamesViewHolder>(GameDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class GamesViewHolder(private val binding: ItemGameBinding) :
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

class GameDiffCallBack : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }
}