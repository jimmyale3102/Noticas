package dev.alejo.noticas.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.alejo.noticas.ui.theme.babyBlue
import dev.alejo.noticas.ui.theme.butterYellow
import dev.alejo.noticas.ui.theme.coralPink
import dev.alejo.noticas.ui.theme.lavender
import dev.alejo.noticas.ui.theme.lightGreen
import dev.alejo.noticas.ui.theme.lightLilac
import dev.alejo.noticas.ui.theme.mintCream
import dev.alejo.noticas.ui.theme.mintGreen
import dev.alejo.noticas.ui.theme.paleTeal
import dev.alejo.noticas.ui.theme.peachCream
import dev.alejo.noticas.ui.theme.pinkViolet
import dev.alejo.noticas.ui.theme.powderLavender
import dev.alejo.noticas.ui.theme.skyBlue
import dev.alejo.noticas.ui.theme.softOrange
import dev.alejo.noticas.ui.theme.warmBeige
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    companion object {
        val noteColors = listOf(
            lavender,
            pinkViolet,
            lightLilac,
            powderLavender,
            coralPink,
            softOrange,
            warmBeige,
            butterYellow,
            peachCream,
            lightGreen,
            mintGreen,
            mintCream,
            paleTeal,
            skyBlue,
            babyBlue
        )
    }
}