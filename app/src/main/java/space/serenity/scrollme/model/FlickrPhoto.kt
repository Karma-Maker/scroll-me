package space.serenity.scrollme.model

/**
 * Created by karmamaker on 31/05/2017.
 */

data class FlickrPhoto(
    val id: Long,
    val secret: String,
    val server: Int,
    val farm: Int,
    val title: String
)