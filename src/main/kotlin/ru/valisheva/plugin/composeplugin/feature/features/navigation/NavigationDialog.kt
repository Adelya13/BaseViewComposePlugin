package ru.valisheva.plugin.composeplugin.feature.features.navigation

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.valisheva.plugin.composeplugin.core.BaseDialog

class NavigationDialog (
    private val viewModel: NavigationViewModel,
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
            row { label("Choose navigation item") }
            row { ""}
            row { checkBox("Bottom Navigation", viewModel::bottomNavigation) }
            row { " "}
            row { checkBox("Drawer", viewModel::drawer) }
            row { " "}
            row { checkBox("Scaffold", viewModel::scaffold) }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.submit()
    }
}