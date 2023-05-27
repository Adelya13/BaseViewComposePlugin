package ru.valisheva.plugin.composeplugin.feature.features.dialog.injection

import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.dialog.DialogViewModel

object DialogViewFactory {
    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : DialogViewModel {
        return DialogViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = DialogFeatureRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor
        )
    }
}