package inburst.com.hackernews.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *  lennyhicks
 *  2/21/19
 */
@Entity(tableName = "articles")
data class Article(val source: Source, val author: String, @PrimaryKey val title: String, val description: String, val url: String, val urlToImage: String, val publishedAt: String, val contents: String)