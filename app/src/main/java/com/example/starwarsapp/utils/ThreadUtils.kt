package fr.mhardy.kotlin_network.utils

import fr.mhardy.kotlin_network.core.Executor
import fr.mhardy.kotlin_network.core.Handler


internal inline fun executeOnBackground(noinline runnable: () -> Unit) {
    Executor.ioExecutorService.submit(runnable)
}

internal inline fun executeOnUi(noinline runnable: () -> Unit) {
    Handler.mainHandler.post(runnable)
}