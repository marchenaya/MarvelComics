package com.marchenaya.marvelcomics.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.base.activity.BaseActivity
import com.marchenaya.marvelcomics.databinding.ActivityMainBinding
import com.marchenaya.marvelcomics.extensions.hide

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController by lazy { findNavController(getNavControllerId()) }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            activityMainToolbar.setupWithNavController(
                navController,
                AppBarConfiguration(
                    setOf(
                        R.id.comic_list_fragment
                    )
                )
            )
            setSupportActionBar(activityMainToolbar)
            activityMainToolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    fun getNavControllerId(): Int = R.id.activity_main_container

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val searchItem = menu?.findItem(R.id.activity_main_menu_search)

        super.onPrepareOptionsMenu(menu)
        binding {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.comic_detail_fragment -> {
                        searchItem?.hide()
                    }
                }
            }
        }
        return true
    }

}
