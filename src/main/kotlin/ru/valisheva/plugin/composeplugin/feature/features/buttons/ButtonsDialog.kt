package ru.valisheva.plugin.composeplugin.feature.features.buttons

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.valisheva.plugin.composeplugin.core.BaseDialog

class ButtonsDialog (
    private val viewModel: ButtonsViewModel,
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
            row { label("Choose Button's types") }
            row { " "}
            row { checkBox("Simple Button With Border Component", viewModel::simpleButtonWithBorderComponent) }
            row { " "}
            row { checkBox("Outlined Button Component", viewModel::outlinedButtonComponent) }
            row { " "}
            row { checkBox("Text Button Component", viewModel::textButtonComponent) }
            row { " "}
            row { checkBox("Rounded Corner Button Component", viewModel::roundedCornerButtonComponent) }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.submit()
    }
}