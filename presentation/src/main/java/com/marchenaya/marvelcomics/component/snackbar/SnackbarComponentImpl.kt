package com.marchenaya.marvelcomics.component.snackbar

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.marchenaya.marvelcomics.R
import com.marchenaya.marvelcomics.component.error.ErrorTranslator
import com.marchenaya.marvelcomics.extensions.getColorFromAttr
import javax.inject.Inject

class SnackbarComponentImpl @Inject constructor(private val errorTranslator: ErrorTranslator) :
    SnackbarComponent {

    override fun displaySuccess(context: Context, content: String, view: View?) {
        view?.let {
            Snackbar.make(it, content, Snackbar.LENGTH_LONG).apply {
                setBackgroundTint(context.getColorFromAttr(R.attr.snackbarSuccessBackground))
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }.show()
        }
    }

    override fun displayWarning(context: Context, content: String, view: View?) {
        view?.let {
            Snackbar.make(it, content, Snackbar.LENGTH_LONG).apply {
                setBackgroundTint(context.getColorFromAttr(R.attr.snackbarWarningBackground))
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }.show()
        }
    }

    override fun displayError(context: Context, throwable: Throwable, view: View?) {
        view?.let {
            Snackbar.make(it, errorTranslator.translate(context, throwable), Snackbar.LENGTH_LONG)
                .apply {
                    setBackgroundTint(context.getColorFromAttr(R.attr.snackbarErrorBackground))
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                }.show()
        }
    }
}