package com.raudonikis.movietracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.raudonikis.movietracker.navigation.NavigationCommand
import com.raudonikis.movietracker.navigation.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeNavigationCommands()
    }

    private fun observeNavigationCommands() {
        navigationHandler.navigationCommands.observe(this) { command ->
            Navigation.findNavController(this, R.id.nav_host_fragment).let { navController ->
                when (command) {
                    is NavigationCommand.To -> navController.navigate(command.directions)
                }
            }
        }
    }
}
