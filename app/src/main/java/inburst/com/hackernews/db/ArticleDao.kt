package inburst.com.hackernews.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import inburst.com.hackernews.models.Article
import io.reactivex.Single

/**
 *  lennyhicks
 *  2/21/19
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getArticles(): Single<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)
}