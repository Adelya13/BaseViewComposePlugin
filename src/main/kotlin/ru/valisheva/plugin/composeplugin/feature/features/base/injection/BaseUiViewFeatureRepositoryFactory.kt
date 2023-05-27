package ru.valisheva.plugin.composeplugin.feature.features.base.injection

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSourceImpl
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.base.domain.BaseUiViewRepository

object BaseUiViewFeatureRepositoryFactory {

    fun create(dependencies: ProjectDependencies) : BaseUiViewRepository {
        return BaseUiViewRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}