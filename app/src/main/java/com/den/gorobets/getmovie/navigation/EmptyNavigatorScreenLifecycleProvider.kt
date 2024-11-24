package com.den.gorobets.getmovie.navigation

import cafe.adriel.voyager.core.lifecycle.NavigatorScreenLifecycleProvider
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen

internal object EmptyNavigatorScreenLifecycleProvider : NavigatorScreenLifecycleProvider {
    override fun provide(screen: Screen): List<ScreenLifecycleOwner> = emptyList()
}