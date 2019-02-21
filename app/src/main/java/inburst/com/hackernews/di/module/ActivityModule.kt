package inburst.com.hackernews.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import inburst.com.hackernews.ui.main.MainContract
import inburst.com.hackernews.ui.main.MainPresenter

/**
 *  lennyhicks
 *  2/21/19
 */
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}