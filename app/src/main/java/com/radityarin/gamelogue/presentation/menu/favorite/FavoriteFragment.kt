package com.radityarin.gamelogue.presentation.menu.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.radityarin.gamelogue.adapter.GameAdapter
import com.radityarin.gamelogue.databinding.FragmentFavoriteBinding
import com.radityarin.gamelogue.presentation.base.BaseFragment
import com.radityarin.gamelogue.presentation.menu.GameViewModel
import com.radityarin.gamelogue.utils.exts.goToDetail
import com.radityarin.gamelogue.utils.exts.showViewWithCondition
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val gameViewModel: GameViewModel by viewModel()
    private lateinit var gameAdapter: GameAdapter

    override fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initData() {
        gameViewModel.getFavoriteGames()
    }

    override fun initRecyclerView() {
        gameAdapter = GameAdapter {
            requireActivity().goToDetail(it)
        }
        binding.rvGame.adapter = gameAdapter
    }

    override fun initObserver() {
        gameViewModel.games.observe(this) { games ->
            with(binding) {
                tvDataNotFound.showViewWithCondition(isShow = games.isEmpty())
                rvGame.showViewWithCondition(isShow = games.isNotEmpty())
            }
            gameAdapter.submitList(games)
        }
    }

    override fun initAppBar() {
        with(binding) {
            appBarLayout.title.text = "Favorite Games"
        }
    }

    override fun initClickListener() {
        super.initClickListener()
        binding.srlRefresh.setOnRefreshListener {
            gameViewModel.getFavoriteGames()
            binding.srlRefresh.isRefreshing = false
        }
    }

}