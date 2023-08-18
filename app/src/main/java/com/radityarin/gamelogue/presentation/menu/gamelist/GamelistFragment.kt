package com.radityarin.gamelogue.presentation.menu.gamelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.radityarin.gamelogue.adapter.GameAdapter
import com.radityarin.gamelogue.adapter.GamePaginationAdapter
import com.radityarin.gamelogue.databinding.FragmentGameListBinding
import com.radityarin.gamelogue.presentation.base.BaseFragment
import com.radityarin.gamelogue.presentation.menu.GameViewModel
import com.radityarin.gamelogue.utils.exts.goToDetail
import com.radityarin.gamelogue.utils.exts.showViewWithCondition
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamelistFragment : BaseFragment() {

    private var _binding: FragmentGameListBinding? = null
    private val binding get() = _binding!!
    private lateinit var gamePaginationAdapter: GamePaginationAdapter
    private val gameViewModel: GameViewModel by viewModel()

    override fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initRecyclerView() {
        gamePaginationAdapter = GamePaginationAdapter {
            requireActivity().goToDetail(it)
        }
        binding.rvGame.adapter = gamePaginationAdapter
    }

    override fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            gameViewModel.getGamesPagination().collectLatest { games ->
                gamePaginationAdapter.submitData(games)
            }
        }
    }

    override fun initAppBar() {
        with(binding) {
            appBarLayout.title.text = "Games For You"
        }
    }

    override fun initEditTextListener() {
        with(binding) {
            search.doAfterTextChanged {
                val query = search.text.toString()
                if (query.isNotBlank()) {
                    gameViewModel.searchGames(query)
                } else {
                    gameViewModel.getAllGames()
                }
            }
        }
    }

}