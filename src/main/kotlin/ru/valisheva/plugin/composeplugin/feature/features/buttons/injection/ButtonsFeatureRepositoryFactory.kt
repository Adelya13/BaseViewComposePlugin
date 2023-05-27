package ru.valisheva.plugin.composeplugin.feature.features.buttons.injection

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSourceImpl
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.buttons.domain.ButtonsRepository

object ButtonsFeatureRepositoryFactory {
    fun create(dependencies: ProjectDependencies) : ButtonsRepository {
        return ButtonsRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}