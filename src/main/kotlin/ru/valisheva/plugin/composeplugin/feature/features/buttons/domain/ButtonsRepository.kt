package ru.valisheva.plugin.composeplugin.feature.features.buttons.domain

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSource

class ButtonsRepository (
    private val dataSource: PreferencesDataSource
) {
    fun put(features: ButtonsView) {
        dataSource.put(TextButtonComponent, features.textButtonComponent)
        dataSource.put(RoundedCornerButtonComponent, features.roundedCornerButtonComponent)
        dataSource.put(SimpleButtonWithBorderComponent, features.simpleButtonWithBorderComponent)
        dataSource.put(OutlinedButtonComponent, features.outlinedButtonComponent)
    }

    fun get() : ButtonsView {
        return ButtonsView(
            textButtonComponent = dataSource.get(TextButtonComponent),
            roundedCornerButtonComponent = dataSource.get(RoundedCornerButtonComponent),
            simpleButtonWithBorderComponent = dataSource.get(SimpleButtonWithBorderComponent),
            outlinedButtonComponent = dataSource.get(OutlinedButtonComponent)
        )
    }

    companion object {
        private const val TextButtonComponent = "Text_Button_Component"
        private const val RoundedCornerButtonComponent = "Rounded_Corner_Button_Component"
        private const val SimpleButtonWithBorderComponent = "Simple_Button_With_Border_Component"
        private const val OutlinedButtonComponent = "Outlined_Button_Component"
    }
}