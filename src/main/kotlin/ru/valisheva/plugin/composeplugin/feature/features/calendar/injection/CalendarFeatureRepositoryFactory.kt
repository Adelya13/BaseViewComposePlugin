package ru.valisheva.plugin.composeplugin.feature.features.calendar.injection

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSourceImpl
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.calendar.domain.CalendarRepository

object CalendarFeatureRepositoryFactory {
    fun create(dependencies: ProjectDependencies) : CalendarRepository {
        return CalendarRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}