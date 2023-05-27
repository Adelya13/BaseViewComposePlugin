package ru.valisheva.plugin.composeplugin.feature.features.calendar.injection

import com.intellij.psi.PsiDirectory
import ru.valisheva.plugin.composeplugin.dependencies.ProjectDependencies
import ru.valisheva.plugin.composeplugin.feature.features.calendar.CalendarViewModel

object CalendarViewFactory {
    fun create(psiDirectory: PsiDirectory, dependencies: ProjectDependencies) : CalendarViewModel {
        return CalendarViewModel(
            directory = psiDirectory,
            generator = dependencies.generator,
            repository = CalendarFeatureRepositoryFactory.create(dependencies),
            editorManager = dependencies.editor
        )
    }
}