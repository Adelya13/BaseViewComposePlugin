package ru.valisheva.plugin.composeplugin.feature.features.base.domain

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSource

class BaseUiViewRepository(
    private val dataSource: PreferencesDataSource
) {
    fun put(features: BaseUiView) {
        dataSource.put(FlowWithLifecycleKey, features.useCollectFlowWithLifecycle)
    }

    fun get() : BaseUiView {
        return BaseUiView(
            useCollectFlowWithLifecycle = dataSource.get(FlowWithLifecycleKey)
        )
    }

    companion object {
        private const val FlowWithLifecycleKey = "use_flow_with_lifecycle"
    }
}