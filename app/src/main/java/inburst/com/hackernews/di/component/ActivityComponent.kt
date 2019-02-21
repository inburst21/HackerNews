package inburst.com.hackernews.di.component

import dagger.Component
import inburst.com.hackernews.di.module.ActivityModule
import inburst.com.hackernews.ui.main.MainActivity

/**
 *  lennyhicks
 *  2/21/19
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}