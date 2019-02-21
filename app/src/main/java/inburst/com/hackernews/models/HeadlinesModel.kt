package inburst.com.hackernews.models

/**
 *  lennyhicks
 *  2/21/19
 */

data class HeadlinesModel(val status: String, val totalResults: Int, val articles: List<Article>)