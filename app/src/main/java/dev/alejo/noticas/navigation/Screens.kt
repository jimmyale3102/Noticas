package dev.alejo.noticas.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens() {
    @Serializable
    data object Notes : Screens()
    @Serializable
    data class AddEditNote(val noteId: Int? = null) : Screens()
}