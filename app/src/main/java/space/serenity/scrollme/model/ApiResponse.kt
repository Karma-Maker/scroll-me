package space.serenity.scrollme.model

/**
 * Created by karmamaker on 31/05/2017.
 */

data class ApiResponse<TData>(
        val photos: Photos<TData>
)

data class Photos<TData>(
        val page: Int,
        val perpage: Int,
        val total: Int,
        val photo: MutableList<TData>
)