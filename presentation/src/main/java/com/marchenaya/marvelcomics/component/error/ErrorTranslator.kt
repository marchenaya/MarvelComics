package com.marchenaya.marvelcomics.component.error

import android.content.Context
import com.marchenaya.data.exception.InvalidParametersException
import com.marchenaya.data.exception.NotFoundException
import com.marchenaya.data.exception.OfflineException
import com.marchenaya.data.exception.RequestFailException
import com.marchenaya.marvelcomics.R
import javax.inject.Inject

class ErrorTranslator @Inject constructor() {

    fun translate(context: Context, throwable: Throwable): String {
        return context.getString(
            when (throwable) {
                is InvalidParametersException, is NotFoundException, is OfflineException, is RequestFailException -> R.string.error_api
                else -> R.string.error_general
            }
        )
    }

}