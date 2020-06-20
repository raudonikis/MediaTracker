package com.raudonikis.movietracker.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.raudonikis.movietracker.extensions.navigate
import com.raudonikis.movietracker.navigation.NavigationCommand

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected abstract val viewModel: BaseViewModel?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel?.navigationCommand?.observe(viewLifecycleOwner) { navigationCommand ->
            when (navigationCommand) {
                is NavigationCommand.To -> navigate(navigationCommand.directions)
            }
        }
    }
}