package com.radityarin.gamelogue.presentation.menu

import android.view.View
import androidx.fragment.app.Fragment
import com.radityarin.gamelogue.R
import com.radityarin.gamelogue.databinding.ActivityMainBinding
import com.radityarin.gamelogue.presentation.base.BaseActivity
import com.radityarin.gamelogue.presentation.menu.favorite.FavoriteFragment
import com.radityarin.gamelogue.presentation.menu.gamelist.GamelistFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val gamelistFragment = GamelistFragment()
    private val favoriteFragment = FavoriteFragment()
    private lateinit var currentFragment: Fragment

    override fun setLayout(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        setFullscreenWindow()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.botNavigationViewMain.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.gamelistFragment -> {
                    setCurrentFragment(gamelistFragment)
                }
                R.id.favoriteFragment -> {
                    setCurrentFragment(favoriteFragment)
                }
            }
            false
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        when (fragment) {
            is GamelistFragment -> {
                binding.botNavigationViewMain.menu.getItem(0).isChecked = true
            }
            is FavoriteFragment -> {
                binding.botNavigationViewMain.menu.getItem(1).isChecked = true
            }
        }
        currentFragment = fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    override fun onStart() {
        super.onStart()
        if (!this::currentFragment.isInitialized) {
            setCurrentFragment(gamelistFragment)
        }
    }

    override fun onResume() {
        super.onResume()
    }

}
