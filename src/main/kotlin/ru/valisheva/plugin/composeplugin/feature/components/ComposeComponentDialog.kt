package ru.valisheva.plugin.composeplugin.feature.components

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.valisheva.plugin.composeplugin.core.BaseDialog

class ComposeComponentDialog(
    private val viewModel: ComposeComponentViewModel
) : BaseDialog() {

    init {
        init()
        viewModel.successFlow
            .onEach { close(0) }
            .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New Jetpack Compose UI Component") }
            row { textField(viewModel::name).focused() }
            noteRow("Creates a new Composable Component and its Preview based on the name given")
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}