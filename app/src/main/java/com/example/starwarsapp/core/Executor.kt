package fr.mhardy.kotlin_network.core

import android.os.Build
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object Executor {

    val ioExecutorService: ExecutorService = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Executors.newWorkStealingPool()
    } else {
        Executors.newCachedThreadPool()
    }

}