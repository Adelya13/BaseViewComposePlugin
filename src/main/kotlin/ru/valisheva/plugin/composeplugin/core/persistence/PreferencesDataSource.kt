package ru.valisheva.plugin.composeplugin.core.persistence

interface PreferencesDataSource {
    fun put(key: String, value: Boolean)
    fun get(key: String) : Boolean
}