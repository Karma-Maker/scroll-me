package space.serenity.scrollme.providers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.serenity.scrollme.model.ApiResponse
import space.serenity.scrollme.model.FlickrPhoto
import space.serenity.scrollme.model.RestAPI

/**
 * Created by karmamaker on 06/07/2017.
 */

class ImagesProvider : Provider() {
    val api = RestAPI()
    var searchQuery = ""
        set(value) {
            if (value != "") {
                field = value // parses the string and assigns values to other properties
                init()
            }
        }

    fun requestPage(pageNumber: Int, pageSize: Int, callback: Callback<ApiResponse<FlickrPhoto>>): Call<ApiResponse<FlickrPhoto>> {
        if(searchQuery.isEmpty()){
            val call = api.getRecentImages(pageNumber, pageSize)
            call.enqueue(callback)
            return call
        } else {
            val call = api.searchImages(searchQuery, pageNumber, pageSize)
            call.enqueue(callback)
            return call
        }
    }

    protected var dataPadding: Int = 0 // Number of items before first item in source
    protected var lastLoadedPageSize = PAGE_SIZE
    private var lastRequestFailed: Boolean = false

    private var currCall: Call<ApiResponse<FlickrPhoto>>? = null

    override fun init() {
        clear()
        currCall = requestPage(0, PAGE_SIZE, callback)
    }

    fun clear() {
        dataPadding = 0
        lastLoadedPageSize = PAGE_SIZE
        val savedCall = currCall
        if (savedCall != null) {
            savedCall.cancel()
            currCall = null
        }
        source.clear()
    }

    private val isFullyLoaded: Boolean
        get() = lastLoadedPageSize != PAGE_SIZE

    private val callback: Callback<ApiResponse<FlickrPhoto>>
        get() = object : Callback<ApiResponse<FlickrPhoto>> {
            override fun onResponse(call: Call<ApiResponse<FlickrPhoto>>, response: Response<ApiResponse<FlickrPhoto>>) {
                if (call?.request().equals(currCall?.request()) && response.body() != null ) {
                    addPage(response.body().photos.photo as MutableList<Any>)
                    onFinish()
                } else {
                    onFailure(call, Throwable("Bad response from server"))
                }
                lastRequestFailed = false
            }

            override fun onFailure(call: Call<ApiResponse<FlickrPhoto>>, t: Throwable) {
                lastRequestFailed = true
                onFinish()

            }

            private fun onFinish() {
                currCall = null
                notifyDataSetChanged()
            }
        }

    override operator fun get(position: Int): Any {
        val currentPage = (position + dataPadding) / PAGE_SIZE

        if ( currCall == null && shouldRequestNextPage(position)) {
            currCall = requestPage(currentPage + 1, PAGE_SIZE, callback)
        }
        return source[position]
    }

    private fun shouldRequestNextPage(position: Int): Boolean {
        return !isFullyLoaded
                && source.size - ITEMS_BEFORE_NEXT_REQUEST < position
                && currCall == null
    }

    private fun addPage(loadedPage: MutableList<Any>) {
        lastLoadedPageSize = loadedPage.size

        val dataSizeWithDuplicates = loadedPage.size
        loadedPage.removeAll(source)

        dataPadding += dataSizeWithDuplicates - loadedPage.size
        source.addAll(source.size, loadedPage)

    }
}
