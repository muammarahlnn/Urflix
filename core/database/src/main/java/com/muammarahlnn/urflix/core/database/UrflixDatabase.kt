package com.muammarahlnn.urflix.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muammarahlnn.urflix.core.database.dao.FilmDao
import com.muammarahlnn.urflix.core.database.model.FilmEntity


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file UrflixDatabase, 03/11/2023 21.13 by Muammar Ahlan Abimanyu
 */
@Database(
    entities = [FilmEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class UrflixDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}