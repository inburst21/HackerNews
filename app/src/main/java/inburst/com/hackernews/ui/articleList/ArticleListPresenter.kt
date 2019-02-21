package inburst.com.hackernews.ui.articleList

import inburst.com.hackernews.api.HackerNewsInterface
import inburst.com.hackernews.models.HeadlinesModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *  lennyhicks
 *  2/21/19
 */
class ArticleListPresenter : ArticleListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: HackerNewsInterface = HackerNewsInterface.create()
    private lateinit var view: ArticleListContract.View

    override fun subscribe() {
        loadTopStories()
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }


    override fun loadTopStories() {
        val subscribe = api.getTopHeadlines().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ model: HeadlinesModel? ->
                    view.showProgress(false)
                    view.loadDataSuccess(model!!.articles)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun loadAllStories() {

        val subscribe = api.getEverything().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ model: HeadlinesModel? ->
                    view.showProgress(false)
                    view.loadDataSuccess(model!!.articles)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun attach(view: ArticleListContract.View) {
        this.view = view
    }
}