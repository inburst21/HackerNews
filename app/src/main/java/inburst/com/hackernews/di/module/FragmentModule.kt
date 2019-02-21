package inburst.com.hackernews.di.module

import dagger.Module
import dagger.Provides
import inburst.com.hackernews.api.HackerNewsInterface
import inburst.com.hackernews.db.ArticleRepository
import inburst.com.hackernews.ui.articleList.ArticleListContract
import inburst.com.hackernews.ui.articleList.ArticleListPresenter

/**
 *  lennyhicks
 *  2/21/19
 */
@Module
class FragmentModule {

    @Provides
    fun provideArticleListPresenter(): ArticleListContract.Presenter {
        return ArticleListPresenter()
    }

    @Provides
    fun provideApiService(): HackerNewsInterface {
        return HackerNewsInterface.create()
    }
}