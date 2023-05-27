package ru.valisheva.plugin.composeplugin.feature.components

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies

class ComposeComponentAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = ComposeComponentViewModel(directory, ProjectDependencies(e.project))
        ComposeComponentDialog(viewModel).show()
    }
}