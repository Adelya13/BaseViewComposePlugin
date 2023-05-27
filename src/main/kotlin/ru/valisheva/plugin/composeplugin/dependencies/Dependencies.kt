package ru.valisheva.plugin.composeplugin.dependencies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object Dependencies {
    val ioScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)
}