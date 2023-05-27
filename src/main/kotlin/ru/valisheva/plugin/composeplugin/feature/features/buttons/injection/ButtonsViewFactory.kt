package ru.valisheva.plugin.composeplugin.feature.features.buttons.injection

import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.buttons.ButtonsViewModel

object ButtonsViewFactory {
    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : ButtonsViewModel {
        return ButtonsViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = ButtonsFeatureRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor
        )
    }
}