package ru.valisheva.plugin.composeplugin.feature.features.buttons

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.valisheva.plugin.composeplugin.core.BaseViewModel
import ru.valisheva.plugin.composeplugin.core.PropertyKeys
import ru.valisheva.plugin.composeplugin.core.TemplateGenerator
import ru.valisheva.plugin.composeplugin.feature.features.buttons.domain.ButtonsRepository

class ButtonsViewModel (
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: ButtonsRepository,
    private val editorManager: FileEditorManager,
) : BaseViewModel() {


    var name: String = ""
        get() = field.capitalize()

    var simpleButtonWithBorderComponent: Boolean
        get() = repository.get().simpleButtonWithBorderComponent
        set(value) = repository.put(repository.get().copy(simpleButtonWithBorderComponent = value))

    var outlinedButtonComponent: Boolean
        get() = repository.get().outlinedButtonComponent
        set(value) = repository.put(repository.get().copy(outlinedButtonComponent = value))

    var roundedCornerButtonComponent: Boolean
        get() = repository.get().roundedCornerButtonComponent
        set(value) = repository.put(repository.get().copy(roundedCornerButtonComponent = value))

    var textButtonComponent: Boolean
        get() = repository.get().textButtonComponent
        set(value) = repository.put(repository.get().copy(textButtonComponent = value))

    val successFlow = MutableSharedFlow<Unit>()

    var createFeaturePackage: Boolean = true

    fun submit() {
        val properties = mutableMapOf<String, Any>(
            PropertyKeys.ListItem to name,
            PropertyKeys.TextButtonComponent to textButtonComponent,
            PropertyKeys.RoundedCornerButtonComponent to roundedCornerButtonComponent,
            PropertyKeys.OutlinedButtonComponent to outlinedButtonComponent,
            PropertyKeys.SimpleButtonWithBorderComponent to simpleButtonWithBorderComponent
        )
        val itemFile = generator.generateKt(
            "Buttons",
            "Buttons",
            directory,
            properties
        )
        editorManager.openFile(itemFile.virtualFile, true)

        scope.launch { successFlow.emit(Unit) }
    }

}