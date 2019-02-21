package inburst.com.hackernews.di.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import inburst.com.hackernews.HackerNewsApp
import inburst.com.hackernews.api.HackerNewsInterface
import inburst.com.hackernews.db.AppDatabase
import inburst.com.hackernews.db.ArticleDao
import inburst.com.hackernews.db.ArticleRepository
import inburst.com.hackernews.di.scope.PerApplication
import javax.inject.Singleton

/**
 *  lennyhicks
 *  2/21/19
 */
@Module
class ApplicationModule(private val baseApp: HackerNewsApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @Singleton
    @PerApplication
    fun provideDatabase(app: Application): ArticleDao {
        val appDatabase = Room.databaseBuilder(app,
                AppDatabase::class.java, "mvvm-database").build()
        return appDatabase.articleDao()
    }

    @Provides
    @Singleton
    @PerApplication
    fun providesArticleRepo(newsInterface: HackerNewsInterface, articleDao: ArticleDao): ArticleRepository{
        return ArticleRepository(newsInterface, articleDao)
    }
}