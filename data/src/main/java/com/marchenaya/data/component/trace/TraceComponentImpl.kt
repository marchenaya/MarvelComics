package com.marchenaya.data.component.trace

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import timber.log.Timber

private const val LOG_FORMAT_LOCAL = "%s; %s; %s; %s"
private const val LOG_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

private const val LOG_ARRAY_PREFIX = "["
private const val LOG_ARRAY_SUFFIX = "]"
private const val LOG_VARIABLE_SEPARATOR = ", "

class TraceComponentImpl @Inject constructor(private val shouldTraceInConsole: Boolean) :
    TraceComponent {

    init {
        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun traceVerbose(id: TraceId, vararg variables: Any?) {
        trace(id, TraceLevel.VERBOSE, false, *variables)
    }

    override fun traceDebug(id: TraceId, vararg variables: Any?) {
        trace(id, TraceLevel.DEBUG, false, *variables)
    }

    override fun traceInfo(id: TraceId, vararg variables: Any?) {
        trace(id, TraceLevel.INFO, false, *variables)
    }

    override fun traceWarning(id: TraceId, vararg variables: Any) {
        trace(id, TraceLevel.WARNING, false, *variables)
    }

    override fun traceError(id: TraceId, vararg variables: Any) {
        trace(id, TraceLevel.ERROR, false, *variables)
    }

    override fun traceCritical(id: TraceId, vararg variables: Any) {
        trace(id, TraceLevel.CRITICAL, false, *variables)
    }

    private fun trace(id: TraceId, level: TraceLevel, vararg variables: Any?) {
        if (shouldTraceInConsole) {
            when (level) {
                TraceLevel.VERBOSE -> Timber.v(formatTrace(id, level, *variables))
                TraceLevel.DEBUG -> Timber.v(formatTrace(id, level, *variables))
                TraceLevel.INFO -> Timber.v(formatTrace(id, level, *variables))
                TraceLevel.WARNING -> Timber.v(formatTrace(id, level, *variables))
                TraceLevel.ERROR -> Timber.v(formatTrace(id, level, *variables))
                TraceLevel.CRITICAL -> Timber.v(formatTrace(id, level, *variables))
            }
        }
    }

    private fun formatTrace(id: TraceId, level: TraceLevel, vararg variables: Any?): String {
        return LOG_FORMAT_LOCAL.format(
            SimpleDateFormat(LOG_DATE_FORMAT, Locale.getDefault()).format(
                Date()
            ),
            level.name,
            id,
            formatVariables(*variables)
        )
    }

    private fun formatVariables(vararg variables: Any?): String {
        val variablesStringBuilder = StringBuilder()
        variables.forEachIndexed { index, variable ->
            when (variable) {
                is Array<*> -> {
                    variablesStringBuilder.append(LOG_ARRAY_PREFIX)
                    variable.forEachIndexed { arrayIndex, arrayItem ->
                        variablesStringBuilder.append(arrayItem.toString())
                        if (arrayIndex != variable.size - 1) {
                            variablesStringBuilder.append(LOG_VARIABLE_SEPARATOR)
                        }
                    }
                    variablesStringBuilder.append(LOG_ARRAY_SUFFIX)
                }
                else -> {
                    variablesStringBuilder.append(variable.toString())
                }
            }
            if (index != variables.size - 1) {
                variablesStringBuilder.append(LOG_VARIABLE_SEPARATOR)
            }
        }
        return variablesStringBuilder.toString()
    }
}
