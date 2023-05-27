package ru.valisheva.plugin.composeplugin.feature.features.calendar

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.valisheva.plugin.composeplugin.core.BaseDialog

class CalendarDialog (
    private val viewModel: CalendarViewModel,
): BaseDialog() {
    init {
        init()
        viewModel
            .successFlow
            .onEach { close(0) }
            .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("Create Calendar Panek") }
            row { checkBox("Also create package for the feature", viewModel::createFeaturePackage) }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.submit()
    }
}