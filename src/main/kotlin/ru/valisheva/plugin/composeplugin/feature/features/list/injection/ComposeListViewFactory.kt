package ru.valisheva.plugin.composeplugin.feature.features.list.injection

import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.list.ComposeListDialogViewModel

object ComposeListViewFactory {

    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : ComposeListDialogViewModel {
        return ComposeListDialogViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = ListViewFeatureRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor
        )
    }
}