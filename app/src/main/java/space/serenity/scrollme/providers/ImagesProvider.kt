package space.serenity.scrollme.providers

import retrofit2.Call
import retrofit2.Callback
import space.serenity.scrollme.model.ApiResponse
import space.serenity.scrollme.model.FlickrPhoto
import space.serenity.scrollme.model.RestAPI

/**
 * Created by karmamaker on 06/07/2017.
 */

class ImagesProvider : PagingProvider<FlickrPhoto>() {
    val api = RestAPI()

    override fun requestPage(pageNumber: Int, pageSize: Int, callback: Callback<ApiResponse<FlickrPhoto>>): Call<ApiResponse<FlickrPhoto>> {
        val call = api.searchImages("space station", pageNumber, pageSize)
        call.enqueue(callback)
        return call
    }
}
