package ru.valisheva.plugin.composeplugin.feature.features.base

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.valisheva.plugin.composeplugin.core.*
import ru.valisheva.plugin.composeplugin.feature.features.base.domain.BaseUiViewRepository

class BaseUiDialogViewModel (
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: BaseUiViewRepository,
    private val editorManager: FileEditorManager,
) : BaseViewModel() {

    var appName: String = ""
        get() = field.capitalize()

    var flowWithLifecycleEnabled: Boolean
        get() = repository.get().useCollectFlowWithLifecycle
        set(value) = repository.put(repository.get().copy(useCollectFlowWithLifecycle = value))

    val successFlow = MutableSharedFlow<Unit>()

    var createFeaturePackage: Boolean = true

    fun submit() {
        val properties = mutableMapOf<String, Any>(
            PropertyKeys.AppName to appName,
        )

        val colorsFile = generator
            .generateKt(
                "MaterialColors",
                "MaterialColors",
                directory,
                properties
            )
        val themeFile = generator
            .generateKt(
                "MaterialTheme",
                "MaterialTheme",
                directory,
                properties
            )
        val typeFile = generator
            .generateKt(
                "MaterialTypographic",
                "MaterialTypographic",
                directory,
                properties
            )
        val activityFile = generator
            .generateKt(
                "MainActivity",
                "MainActivity",
                directory,
                properties
            )

        editorManager.openFile(colorsFile.virtualFile, true)
        editorManager.openFile(themeFile.virtualFile, false)
        editorManager.openFile(typeFile.virtualFile, false)
        editorManager.openFile(activityFile.virtualFile, false)
        scope.launch { successFlow.emit(Unit) }
    }

}