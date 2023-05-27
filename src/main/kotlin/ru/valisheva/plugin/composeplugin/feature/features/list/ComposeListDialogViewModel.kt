package ru.valisheva.plugin.composeplugin.feature.features.list

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.valisheva.plugin.composeplugin.core.BaseViewModel
import ru.valisheva.plugin.composeplugin.core.PropertyKeys
import ru.valisheva.plugin.composeplugin.core.TemplateGenerator
import ru.valisheva.plugin.composeplugin.feature.features.list.domain.ListViewRepository

class ComposeListDialogViewModel(
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: ListViewRepository,
    private val editorManager: FileEditorManager,
) : BaseViewModel() {


    var name: String = ""
        get() = field.capitalize()

    var flowWithLifecycleEnabled: Boolean
        get() = repository.get().useCollectFlowWithLifecycle
        set(value) = repository.put(repository.get().copy(useCollectFlowWithLifecycle = value))

    val successFlow = MutableSharedFlow<Unit>()

    var createFeaturePackage: Boolean = true

    fun submit() {
        val properties = mutableMapOf<String, Any>(
            PropertyKeys.ListItem to name,
        )
        val itemFile = generator.generateKt(
                "ListView",
                "ListView",
                directory,
                properties
            )
        val listFile = generator.generateKt(
            "List",
            "List",
            directory,
            properties
        )
        editorManager.openFile(itemFile.virtualFile, true)
        editorManager.openFile(listFile.virtualFile, false)

        scope.launch { successFlow.emit(Unit) }
    }

}