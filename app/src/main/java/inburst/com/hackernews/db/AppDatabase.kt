package inburst.com.hackernews.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import inburst.com.hackernews.models.Article

/**
 *  lennyhicks
 *  2/21/19
 */
@Database(entities = arrayOf(Article::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}