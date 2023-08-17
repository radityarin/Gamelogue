package com.radityarin.gamelogue.presentation.menu.details

import android.text.Html
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.radityarin.gamelogue.R
import com.radityarin.gamelogue.databinding.ActivityDetailGameBinding
import com.radityarin.gamelogue.presentation.base.BaseActivity
import com.radityarin.gamelogue.presentation.menu.GameViewModel
import com.radityarin.gamelogue.utils.exts.addPlayed
import com.radityarin.gamelogue.utils.exts.addReleaseDate
import com.radityarin.gamelogue.utils.exts.getGame
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailGameBinding
    private val gameViewModel: GameViewModel by viewModel()

    override fun setLayout(): View {
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        setFullscreenWindow()
    }

    override fun initData() {
        gameViewModel.getGamesDetail(intent.getGame())
    }

    override fun initObserver() {
        with(binding) {
            gameViewModel.game.observe(this@DetailGameActivity) {
                Glide.with(applicationContext)
                    .load(it.backgroundImage)
                    .into(ivGame)
                tvTitle.text = it.name
                tvReleaseDate.text = it.released.addReleaseDate()
                tvRating.text = it.rating.toString()
                tvDescription.text = Html.fromHtml(it.description, Html.FROM_HTML_MODE_LEGACY)
                tvGamePlayed.text = it.added.toInt().toString().addPlayed()
                tvPublisher.text = it.publisher
            }

            gameViewModel.isGameFavorite.observe(this@DetailGameActivity) {
                changeFavoriteIcon(it)
            }
        }
    }

    private fun changeFavoriteIcon(it: Boolean) {
        with(binding) {
            val icon =
                if (it) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            appBarLayout.ivFavorite.setImageResource(icon)
        }
    }

    override fun initClickListener() {
        with(binding) {
            appBarLayout.tvBack.setOnClickListener {
                finish()
            }
            appBarLayout.ivFavorite.setOnClickListener {
                gameViewModel.updateFavoriteGame(intent.getGame())
                Toast.makeText(applicationContext, "Movie Favorite Updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}