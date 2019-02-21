package inburst.com.hackernews.di.component

import dagger.Component
import inburst.com.hackernews.HackerNewsApp
import inburst.com.hackernews.di.module.ApplicationModule

/**
 *  lennyhicks
 *  2/21/19
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: HackerNewsApp)

}