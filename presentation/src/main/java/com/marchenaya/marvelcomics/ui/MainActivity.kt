package com.marchenaya.marvelcomics.ui

import android.view.LayoutInflater
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.base.activity.BaseActivity
import com.marchenaya.marvelcomics.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    fun getNavControllerId(): Int = R.id.activity_main_container

}
