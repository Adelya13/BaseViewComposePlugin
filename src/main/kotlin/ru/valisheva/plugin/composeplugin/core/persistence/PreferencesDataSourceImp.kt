package ru.valisheva.plugin.composeplugin.core.persistence

import com.intellij.ide.util.PropertiesComponent
import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSource

class PreferencesDataSourceImpl(
    private val component: PropertiesComponent
) : PreferencesDataSource {
    override fun put(key: String, value: Boolean) {
        component.setValue(key.projectKey, value)
    }

    override fun get(key: String): Boolean {
        return component.getBoolean(key.projectKey)
    }

    private val String.projectKey: String get() {
        return "ru.valisheva.plugin.composeplugin_$this"
    }
}