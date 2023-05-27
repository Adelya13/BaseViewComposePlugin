package ru.valisheva.plugin.composeplugin.feature.features.list.domain

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSource

class ListViewRepository(
    private val dataSource: PreferencesDataSource
) {
    fun put(features: ListView) {
        dataSource.put(FlowWithLifecycleKey, features.useCollectFlowWithLifecycle)
    }

    fun get() : ListView {
        return ListView(
            useCollectFlowWithLifecycle = dataSource.get(FlowWithLifecycleKey)
        )
    }

    companion object {
        private const val FlowWithLifecycleKey = "use_flow_with_lifecycle"
    }
}