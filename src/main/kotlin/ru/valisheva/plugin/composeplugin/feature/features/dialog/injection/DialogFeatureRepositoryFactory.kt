package ru.valisheva.plugin.composeplugin.feature.features.dialog.injection

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSourceImpl
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.dialog.domain.DialogRepository

object DialogFeatureRepositoryFactory {
    fun create(dependencies: ProjectDependencies) : DialogRepository {
        return DialogRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}