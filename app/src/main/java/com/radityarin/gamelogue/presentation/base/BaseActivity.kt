package com.radityarin.gamelogue.presentation.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        initAppBar()
        handleIntentData()
        initRecyclerView()
        onCreateActivity()
        initObserver()
        initEditTextListener()
        initClickListener()
        initView()
        initData()
        initSearchView()
        initSpinner()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyActivity()
    }

    abstract fun setLayout(): View

    open fun initAppBar() {}

    open fun initSearchView() {}

    open fun onCreateActivity() {}

    open fun onDestroyActivity() {}

    open fun initView() {}

    open fun initData() {}

    open fun initRecyclerView() {}

    open fun handleIntentData() {}

    open fun initObserver() {}

    open fun initSpinner() {}

    open fun initEditTextListener() {}

    open fun initClickListener() {}

    fun setFullscreenWindow() {
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE
        }
    }

}