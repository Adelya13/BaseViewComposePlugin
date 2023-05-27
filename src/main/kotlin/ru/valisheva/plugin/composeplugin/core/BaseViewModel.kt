package ru.valisheva.plugin.composeplugin.core

import ru.valisheva.plugin.composeplugin.dependencies.Dependencies


abstract class BaseViewModel() {
    protected val scope = Dependencies.ioScope
}