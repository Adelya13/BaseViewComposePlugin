package ru.valisheva.plugin.composeplugin.feature.features.navigation.injection

import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.navigation.NavigationViewModel

object NavigationViewFactory {
    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : NavigationViewModel {
        return NavigationViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = NavigationFeatureRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor
        )
    }
}