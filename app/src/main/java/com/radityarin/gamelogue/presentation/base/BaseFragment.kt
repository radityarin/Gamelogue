package com.radityarin.gamelogue.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private lateinit var currentLoadingDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setLayout(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppBar()
        handleIntentData()
        onCreateActivity()
        initObserver()
        initRecyclerView()
        initEditTextListener()
        initClickListener()
        initView()
        initData()
        initTabLayout()
        initSearchView()
    }

    abstract fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View

    open fun initAppBar() {}

    open fun onCreateActivity() {}

    open fun onDestroyActivity() {}

    open fun initView() {}

    open fun initData() {}

    open fun initRecyclerView() {}

    open fun handleIntentData() {}

    open fun initObserver() {}

    open fun initTabLayout() {}

    open fun initSearchView() {}

    open fun initEditTextListener() {}

    open fun initClickListener() {}

}