package ru.valisheva.plugin.composeplugin.feature.features.dialog

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.valisheva.plugin.composeplugin.core.BaseViewModel
import ru.valisheva.plugin.composeplugin.core.PropertyKeys
import ru.valisheva.plugin.composeplugin.core.TemplateGenerator
import ru.valisheva.plugin.composeplugin.feature.features.dialog.domain.DialogRepository

class DialogViewModel (
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: DialogRepository,
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
            "AlertDialog",
            "AlertDialog",
            directory,
            properties
        )
        editorManager.openFile(itemFile.virtualFile, true)

        scope.launch { successFlow.emit(Unit) }
    }

}