package ru.valisheva.plugin.composeplugin.feature.features.list

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.list.injection.ComposeListViewFactory

class ComposeListAction: AnAction()  {
    override fun actionPerformed(e: AnActionEvent) {
        val deps = ProjectDependencies(e.project)
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = ComposeListViewFactory.create(directory, deps)
        ComposeListDialog(viewModel).show()
    }
}
