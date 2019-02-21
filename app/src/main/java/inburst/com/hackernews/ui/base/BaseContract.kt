package inburst.com.hackernews.ui.base

/**
 *  lennyhicks
 *  2/21/19
 */
class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {

    }
}