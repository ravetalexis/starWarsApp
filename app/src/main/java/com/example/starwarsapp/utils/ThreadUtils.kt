package com.example.starwarsapp.utils

import com.example.starwarsapp.core.Executor
import com.example.starwarsapp.core.Handler


internal inline fun executeOnBackground(noinline runnable: () -> Unit) {
    Executor.ioExecutorService.submit(runnable)
}

internal inline fun executeOnUi(noinline runnable: () -> Unit) {
    Handler.mainHandler.post(runnable)
}