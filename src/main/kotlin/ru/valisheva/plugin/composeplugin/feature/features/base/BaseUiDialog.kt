package ru.valisheva.plugin.composeplugin.feature.features.base

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.valisheva.plugin.composeplugin.core.BaseDialog

class BaseUiDialog(
    private val viewModel: BaseUiDialogViewModel,
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
            row { label("Set name of Material Base Components") }
            row { textField(viewModel::appName).focused() }
            row { checkBox("Also create package for the feature", viewModel::createFeaturePackage) }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.submit()
    }
}