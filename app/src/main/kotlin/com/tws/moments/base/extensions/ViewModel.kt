package com.tws.moments.base.extensions

import androidx.core.util.Consumer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun ViewModel.withSafeScope(
    onLoading: Consumer<Boolean>? = null,
    onError: Consumer<Exception>? = null,
    onComplete: Runnable? = null,
    block: suspend () -> Unit,
) = viewModelScope.launch {
    onLoading?.accept(true)
    try {
        block()
        onComplete?.run()
    } catch (e: Exception) {
        onError?.accept(e)
    }
}