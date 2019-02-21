package inburst.com.hackernews.ui.articleList

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import inburst.com.hackernews.R
import inburst.com.hackernews.models.Article

/**
 *  lennyhicks
 *  2/21/19
 */
class ArticleListAdapter(private val context: Context, private val list: List<Article>,
                  fragment: Fragment): RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder>() {


    private val listener: ArticleListAdapter.onItemClickListener

    init {
        this.listener = fragment as ArticleListAdapter.onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        var currArticle = list[position]

         holder.bind(currArticle)

        holder.itemView.setOnClickListener {
            listener.openArticle(currArticle.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_article_listitem, parent, false)
        return ArticleListAdapter.ArticleListViewHolder(itemView)
    }


    class ArticleListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.text_title)
        val description = itemView.findViewById<TextView>(R.id.text_description)
        val author = itemView.findViewById<TextView>(R.id.text_author)
        val source = itemView.findViewById<TextView>(R.id.text_source)

        fun bind(item: Article) {
            title.text = item.title
            description.text = item.description
            author.text = if(!item.author.isNullOrEmpty()) item.author else "Unknown"
            source.text = item.source.name
        }
    }

    interface onItemClickListener {
        fun openArticle(url: String)
    }
}