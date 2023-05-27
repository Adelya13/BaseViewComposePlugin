package ru.valisheva.plugin.composeplugin.feature.features.calendar

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.valisheva.plugin.composeplugin.core.BaseViewModel
import ru.valisheva.plugin.composeplugin.core.PropertyKeys
import ru.valisheva.plugin.composeplugin.core.TemplateGenerator
import ru.valisheva.plugin.composeplugin.feature.features.calendar.domain.CalendarRepository

class CalendarViewModel (
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: CalendarRepository,
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
        val calendar = generator.generateKt(
            "Calendar",
            "Calendar",
            directory,
            properties
        )
        val day = generator.generateKt(
            "Day",
            "Day",
            directory,
            properties
        )
        val month = generator.generateKt(
            "Month",
            "Month",
            directory,
            properties
        )
        val week = generator.generateKt(
            "Week",
            "Week",
            directory,
            properties
        )


        editorManager.openFile(calendar.virtualFile, true)
        editorManager.openFile(day.virtualFile, false)
        editorManager.openFile(month.virtualFile, false)
        editorManager.openFile(week.virtualFile, false)

        scope.launch { successFlow.emit(Unit) }
    }

}