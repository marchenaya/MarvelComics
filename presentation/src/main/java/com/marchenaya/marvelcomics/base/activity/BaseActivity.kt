package com.marchenaya.marvelcomics.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = viewBinding as VB

    private var viewBinding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewBinding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(viewBinding).root)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    fun binding(receiver: VB.() -> Unit) = receiver(binding)
}
