package com.muammarahlnn.urflix.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmEntity, 03/11/2023 21.01 by Muammar Ahlan Abimanyu
 */
@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("film_id") val filmId: Int,
    val title: String,
    @ColumnInfo("release_date") val releaseDate: String,
    @ColumnInfo("vote_average") val voteAverage: Float,
    @ColumnInfo("poster_image") val posterImage: String,
    @ColumnInfo("film_type") val filmTypeOrdinal: Int,
)