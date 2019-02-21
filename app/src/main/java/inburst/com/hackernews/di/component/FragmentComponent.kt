package inburst.com.hackernews.di.component

import dagger.Component
import inburst.com.hackernews.di.module.FragmentModule
import inburst.com.hackernews.ui.articleList.ArticleListFragment

/**
 *  lennyhicks
 *  2/21/19
 */
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(aboutFragment: ArticleListFragment)

}