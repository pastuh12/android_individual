package com.example.individual.utils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

val defaultErrorHandler: CoroutineContext = CoroutineExceptionHandler { _, error ->
    Log.e(
        null,
        error.message,
        error
    )
}