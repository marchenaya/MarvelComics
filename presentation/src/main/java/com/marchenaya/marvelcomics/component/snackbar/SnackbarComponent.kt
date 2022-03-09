package com.marchenaya.marvelcomics.component.snackbar

import android.content.Context
import android.view.View

interface SnackbarComponent {

    fun displaySuccess(context: Context, content: String, view: View?)

    fun displayWarning(context: Context, content: String, view: View?)

    fun displayError(context: Context, throwable: Throwable, view: View?)

}