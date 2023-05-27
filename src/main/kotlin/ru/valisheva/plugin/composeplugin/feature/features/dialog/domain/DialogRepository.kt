package ru.valisheva.plugin.composeplugin.feature.features.dialog.domain

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSource

class DialogRepository (
    private val dataSource: PreferencesDataSource
) {
    fun put(features: DialogView) {
        dataSource.put(FlowWithLifecycleKey, features.useCollectFlowWithLifecycle)
    }

    fun get() : DialogView {
        return DialogView(
            useCollectFlowWithLifecycle = dataSource.get(FlowWithLifecycleKey)
        )
    }

    companion object {
        private const val FlowWithLifecycleKey = "use_flow_with_lifecycle"
    }
}