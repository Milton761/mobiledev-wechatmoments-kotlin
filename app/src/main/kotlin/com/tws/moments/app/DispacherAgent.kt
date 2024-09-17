package com.tws.moments.app

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherAgent {

    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    val default: CoroutineDispatcher
        get() = Dispatchers.Default

    val main: CoroutineDispatcher
        get() = Dispatchers.Main
}