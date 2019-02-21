package inburst.com.hackernews.ui.main

import io.reactivex.disposables.CompositeDisposable

/**
 *  lennyhicks
 *  2/21/19
 */
class MainPresenter: MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showStories()
    }

}