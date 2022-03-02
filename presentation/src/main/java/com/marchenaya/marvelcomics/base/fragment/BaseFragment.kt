package com.marchenaya.marvelcomics.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var viewBinding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = viewBinding as VB

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(viewBinding).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    fun binding(receiver: VB.() -> Unit) {
        receiver(binding)
    }
}
