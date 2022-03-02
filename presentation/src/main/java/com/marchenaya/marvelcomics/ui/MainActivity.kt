package com.marchenaya.marvelcomics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.base.activity.BaseActivity
import com.marchenaya.marvelcomics.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.activityMainToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    fun getNavControllerId(): Int = R.id.activity_main_container

}
