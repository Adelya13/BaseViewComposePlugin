package ru.valisheva.plugin.composeplugin.feature.features.navigation.domain

import ru.valisheva.plugin.composeplugin.core.persistence.PreferencesDataSource

class NavigationRepository (
    private val dataSource: PreferencesDataSource
) {
    fun put(features: NavigationView) {
        dataSource.put(Scaffold, features.scaffold)
        dataSource.put(BottomNavigation, features.bottomNavigation)
        dataSource.put(Drawer, features.drawer)
    }

    fun get() : NavigationView {
        return NavigationView(
            scaffold  = dataSource.get(Scaffold),
            bottomNavigation  = dataSource.get(BottomNavigation),
            drawer  = dataSource.get(Drawer),
        )
    }

    companion object {
        private const val Scaffold = "Scaffold"
        private const val BottomNavigation = "BottomNavigation"
        private const val Drawer = "Drawer"
    }
}