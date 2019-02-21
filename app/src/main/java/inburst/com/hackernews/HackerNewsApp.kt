package inburst.com.hackernews

import android.app.Application
import inburst.com.hackernews.di.component.ApplicationComponent
import inburst.com.hackernews.di.component.DaggerApplicationComponent
import inburst.com.hackernews.di.module.ApplicationModule

/**
 *  lennyhicks
 *  2/21/19
 */
class HackerNewsApp: Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: HackerNewsApp private set
    }
}