package dev.alejo.noticas.navigation

import kotlinx.serialization.Serializable

sealed class Screens() {
    @Serializable
    data object Notes : Screens()
    @Serializable
    data object AddEditNote : Screens()
}