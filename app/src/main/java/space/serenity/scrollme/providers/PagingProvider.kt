package space.serenity.scrollme.providers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.serenity.scrollme.model.ApiResponse

//import retrofit2.Response

/**
 * Created by karmamaker on 05/07/2017.
 */

abstract class PagingProvider <TData> : Provider {
    constructor() : super()

    protected var dataPadding: Int = 0 // Number of items before first item in source
    protected var lastLoadedPageSize = PAGE_SIZE
    private var lastRequestFailed: Boolean = false

    private var currCall: Call<ApiResponse<TData>>? = null

    override fun init() {
        clear()
        currCall = requestPage(0, PAGE_SIZE, callback)
        source.add(ITEM_LOADING_FULLSCREEN)
    }

    fun clear() {
        dataPadding = 0
        lastLoadedPageSize = PAGE_SIZE
        val savedCall = currCall
        if (savedCall != null) {
            savedCall.cancel()
            currCall = null
        }
    }

    private val isFullyLoaded: Boolean
        get() = lastLoadedPageSize != PAGE_SIZE

    abstract fun requestPage(pageNumber: Int, pageSize: Int, callback: Callback<ApiResponse<TData>>): Call<ApiResponse<TData>>

    private val callback: Callback<ApiResponse<TData>>
        get() = object : Callback<ApiResponse<TData>> {
            override fun onResponse(call: Call<ApiResponse<TData>>, response: Response<ApiResponse<TData>>) {
                beforeResponse()
                if (response.body() != null) {
                    addPage(response.body().photos.photo as MutableList<Any>)
                    onFinish()
                } else {
                    onFailure(call, Throwable("Bad response from server"))
                }
                lastRequestFailed = false
            }

            override fun onFailure(call: Call<ApiResponse<TData>>, t: Throwable) {
//                beforeResponse()
//                if (source.isEmpty()) {
//                    source.add(ReviewsProvider.ITEM_NO_CONNECTION_FULLSCREEN)
//                } else {
//                    source.add(ReviewsProvider.ITEM_NO_CONNECTION_SMALL)
//                }
                lastRequestFailed = true
                onFinish()

            }

            private fun onFinish() {
                currCall = null
                notifyDataSetChanged()
            }

            private fun beforeResponse() {
//                source.remove(ReviewsProvider.ITEM_NO_CONNECTION_FULLSCREEN) // Not reliable but let it be for the time beeng
//                source.remove(ReviewsProvider.ITEM_NO_CONNECTION_SMALL)
//                source.remove(ReviewsProvider.ITEM_LOADING)
//                source.remove(ReviewsProvider.ITEM_LOADING_FULLSCREEN)
            }
        }

    override operator fun get(position: Int): Any {
        val currentPage = (position + dataPadding) / PAGE_SIZE

        if ( currCall == null && shouldRequestNextPage(position)) {
            source.add(ITEM_LOADING_SMALL)
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