package inburst.com.hackernews.ui.articleList

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import inburst.com.hackernews.R
import inburst.com.hackernews.di.component.DaggerFragmentComponent
import inburst.com.hackernews.di.module.FragmentModule
import inburst.com.hackernews.models.Article
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

/**
 *  lennyhicks
 *  2/21/19
 */

class ArticleListFragment: Fragment(), ArticleListContract.View, ArticleListAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: ArticleListContract.Presenter


    private lateinit var rootView: View

    fun newInstance(): ArticleListFragment {
        return ArticleListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        // get reference to button
        val loadtop = view.findViewById(R.id.loadtop) as Button
        val loadall = view.findViewById(R.id.loadall) as Button

        loadall.setOnClickListener{
            presenter.loadAllStories()
        }

        loadtop.setOnClickListener {
            presenter.loadTopStories()
        }
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(list: List<Article>) {
        var adapter = ArticleListAdapter(requireContext(), list, this)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.adapter = adapter


    }

    private fun initView() {
        presenter.attach(this)
    }

    override fun openArticle(url: String) {
        openWebpage(url)
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    companion object {
        val TAG: String = "ListFragment"
    }

    private fun openWebpage(url: String) {
        val builder = CustomTabsIntent.Builder()
        builder.enableUrlBarHiding()
        builder.setShowTitle(true)
        builder.setToolbarColor(Color.TRANSPARENT)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(activity, Uri.parse(url))
    }
}