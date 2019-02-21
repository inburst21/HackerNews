package inburst.com.hackernews.db

import inburst.com.hackernews.api.HackerNewsInterface
import inburst.com.hackernews.models.Article
import inburst.com.hackernews.models.HeadlinesModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 *  lennyhicks
 *  2/21/19
 */
class ArticleRepository(val newsRepo: HackerNewsInterface, val newsDb: ArticleDao) {

    fun getNews(): Observable<List<Article>> {
        return Observable.concatArray(
                getArticlesFromDb(),
                getArticlesFromApi())
    }

    fun getTopHeadlines(): Observable<HeadlinesModel> {
        return newsRepo.getTopHeadlines()
    }

    fun getArticlesFromDb(): Observable<List<Article>> {
        return newsDb.getArticles().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} users from DB...")
                }
    }

    fun getArticlesFromApi(): Observable<List<Article>> {
        var articles: List<Article> = emptyList()
        val repo = newsRepo.getTopHeadlines()
                .doOnNext {
                    Timber.d("Dispatching ${it.articles.size} users from API...")
                    storeUsersInDb(it.articles)
                    articles = it.articles
                }
        return Observable.fromArray(articles)
    }

    fun storeUsersInDb(articles: List<Article>) {
        Observable.fromCallable { newsDb.insertAll(articles) }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${articles.size} users from API in DB...")
                }
    }



}