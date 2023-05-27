package ru.valisheva.plugin.composeplugin.feature.features.list.injection

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSourceImpl
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.list.domain.ListViewRepository

object ListViewFeatureRepositoryFactory {

    fun create(dependencies: ProjectDependencies) : ListViewRepository {
        return ListViewRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}