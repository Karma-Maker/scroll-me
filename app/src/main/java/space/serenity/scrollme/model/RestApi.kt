package space.serenity.scrollme.model

import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


/**
 * Created by karmamaker on 31/05/2017.
 */

class RestAPI {
    private val flickrApi: FlickrApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.getyourguide.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    fun getRecentImages(page: Int, count: Int): Call<ApiResponse<FlickrPhoto>> {
        return flickrApi.getReviews(method = "flickr.photos.getRecent",
                apiKey = "3e7cc266ae2b0e0d78e279ce8e361736",
                format = "json",
                nojsoncallback = 1,
                safeSearch = 1,
                searchQuery = "",
                page = page,
                perPage = count)
    }

    fun searchImages(searchQuery: String, page: Int, count: Int): Call<ApiResponse<FlickrPhoto>> {
        return flickrApi.getReviews(method = "flickr.photos.search",
                apiKey = "3e7cc266ae2b0e0d78e279ce8e361736",
                format = "json",
                nojsoncallback = 1,
                safeSearch = 1,
                searchQuery = searchQuery,
                page = page,
                perPage = count)
    }
}

interface FlickrApi {
    @GET("https://api.flickr.com/services/rest/")
    fun getReviews(@Query("method") method: String,
                   @Query("api_key") apiKey: String,
                   @Query("format") format: String,
                   @Query("nojsoncallback") nojsoncallback: Int,
                   @Query("safe_search") safeSearch: Int,
                   @Query("text") searchQuery: String,
                   @Query("page") page: Int,
                   @Query("per_page") perPage: Int) : Call<ApiResponse<FlickrPhoto>>
}