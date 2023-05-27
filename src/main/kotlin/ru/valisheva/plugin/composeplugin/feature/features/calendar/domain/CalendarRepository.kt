package ru.valisheva.plugin.composeplugin.feature.features.calendar.domain

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSource

class CalendarRepository(
    private val dataSource: PreferencesDataSource
) {
    fun put(features: CalendarView) {
        dataSource.put(FlowWithLifecycleKey, features.useCollectFlowWithLifecycle)
    }

    fun get() : CalendarView {
        return CalendarView(
            useCollectFlowWithLifecycle = dataSource.get(FlowWithLifecycleKey)
        )
    }

    companion object {
        private const val FlowWithLifecycleKey = "use_flow_with_lifecycle"
    }
}