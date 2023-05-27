package ru.valisheva.plugin.composeplugin.feature.features.navigation.injection

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSourceImpl
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.calendar.domain.CalendarRepository
import ru.valisheva.plugin.composeplugin.feature.features.navigation.domain.NavigationRepository

object NavigationFeatureRepositoryFactory {
    fun create(dependencies: ProjectDependencies) : NavigationRepository {
        return NavigationRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}