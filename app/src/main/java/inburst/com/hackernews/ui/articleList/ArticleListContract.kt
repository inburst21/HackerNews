package inburst.com.hackernews.ui.articleList

import inburst.com.hackernews.models.Article
import inburst.com.hackernews.ui.base.BaseContract

/**
 *  lennyhicks
 *  2/21/19
 */

class ArticleListContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Article>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadTopStories()
        fun loadAllStories()
    }
}