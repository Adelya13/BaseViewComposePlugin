package ru.valisheva.plugin.composeplugin.feature.features.base

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.base.injection.BaseUiViewFactory

class BaseUiAction: AnAction()  {
    override fun actionPerformed(e: AnActionEvent) {
        val dependencies = ProjectDependencies(e.project)
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = BaseUiViewFactory.create(directory, dependencies)
        BaseUiDialog(viewModel).show()
    }
}