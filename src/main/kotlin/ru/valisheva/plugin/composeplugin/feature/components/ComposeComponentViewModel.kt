package ru.valisheva.plugin.composeplugin.feature.components

import com.intellij.psi.PsiDirectory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.valisheva.plugin.composeplugin.core.BaseViewModel
import ru.valisheva.plugin.composeplugin.core.PropertyKeys
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies

class ComposeComponentViewModel(
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel(){
    var name: String = ""
        get() = field.capitalize()
    val successFlow = MutableSharedFlow<Unit>()

    fun onOkButtonClick() {
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.ListItem to name)
        val file = projectDependencies.generator.generateKt("ComposeComponent", name, directory, properties)
        projectDependencies.editor.openFile(file.virtualFile, true)
        scope.launch { successFlow.emit(Unit) }
    }
}