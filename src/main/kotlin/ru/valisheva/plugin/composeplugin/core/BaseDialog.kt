package ru.valisheva.plugin.composeplugin.core

import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import ru.valisheva.plugin.composeplugin.dependencies.Dependencies
import javax.swing.JComponent


abstract class BaseDialog: DialogWrapper(true) {

    lateinit var panel: DialogPanel
    protected val dialogScope = Dependencies.mainScope


    override fun createCenterPanel(): JComponent? {
        panel = createPanel()
        return panel
    }

    abstract fun createPanel() : DialogPanel
}