package ru.valisheva.plugin.composeplugin.feature.features.base.injection

import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.base.BaseUiDialogViewModel

object BaseUiViewFactory {

    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : BaseUiDialogViewModel {
        return BaseUiDialogViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = BaseUiViewFeatureRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor
        )
    }
}