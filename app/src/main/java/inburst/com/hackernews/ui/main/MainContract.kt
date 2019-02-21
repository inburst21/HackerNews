package inburst.com.hackernews.ui.main

import inburst.com.hackernews.ui.base.BaseContract

/**
 *  lennyhicks
 *  2/21/19
 */
class MainContract {

    interface View: BaseContract.View {
        fun showStories();
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {

    }
}