package inburst.com.hackernews.api

import inburst.com.hackernews.models.HeadlinesModel
import inburst.com.hackernews.utils.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers



/**
 *  lennyhicks
 *  2/21/19
 */
interface HackerNewsInterface {

    @Headers("X-Api-Key: ${Constants.API_KEY}")
    @GET(value = "top-headlines?country=us")
    fun getTopHeadlines(): Observable<HeadlinesModel>

    @Headers("X-Api-Key: ${Constants.API_KEY}")
    @GET(value = "everything?q=bitcoin")
    fun getEverything(): Observable<HeadlinesModel>

    companion object Factory {
        fun create(): HackerNewsInterface {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .build()

            return retrofit.create(HackerNewsInterface::class.java)
        }
    }
}