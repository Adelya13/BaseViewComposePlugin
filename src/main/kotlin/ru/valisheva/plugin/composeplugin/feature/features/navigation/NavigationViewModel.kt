package ru.valisheva.plugin.composeplugin.feature.features.navigation

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.valisheva.plugin.composeplugin.core.BaseViewModel
import ru.valisheva.plugin.composeplugin.core.PropertyKeys
import ru.valisheva.plugin.composeplugin.core.TemplateGenerator
import ru.valisheva.plugin.composeplugin.feature.features.navigation.domain.NavigationRepository

class NavigationViewModel (
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: NavigationRepository,
    private val editorManager: FileEditorManager,
) : BaseViewModel() {


    var name: String = ""
        get() = field.capitalize()

    var bottomNavigation: Boolean
        get() = repository.get().bottomNavigation
        set(value) = repository.put(repository.get().copy(bottomNavigation = value))

    var scaffold: Boolean
        get() = repository.get().scaffold
        set(value) = repository.put(repository.get().copy(scaffold = value))

    var drawer: Boolean
        get() = repository.get().drawer
        set(value) = repository.put(repository.get().copy(drawer = value))

    val successFlow = MutableSharedFlow<Unit>()

    var createFeaturePackage: Boolean = true

    fun submit() {
        val properties = mutableMapOf<String, Any>(
            PropertyKeys.ListItem to name,
            PropertyKeys.Scaffold to scaffold,
            PropertyKeys.Drawer to drawer,
            PropertyKeys.BottomNavigation to bottomNavigation
        )
        if (bottomNavigation){
            val bottomNavigation = generator.generateKt(
                "BottomNavigation",
                "BottomNavigation",
                directory,
                properties
            )
            editorManager.openFile(bottomNavigation.virtualFile, false)

        }
        if (drawer){
            val drawer = generator.generateKt(
                "Drawer",
                "Drawer",
                directory,
                properties
            )
            editorManager.openFile(drawer.virtualFile, true)
        }
        if (scaffold){
            val scaffold = generator.generateKt(
                "Scaffold",
                "Scaffold",
                directory,
                properties
            )

            editorManager.openFile(scaffold.virtualFile, false)
        }
        scope.launch { successFlow.emit(Unit) }
    }

}