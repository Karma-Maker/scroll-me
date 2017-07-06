package space.serenity.scrollme.binders

import android.graphics.Color
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import space.serenity.scrollme.R
import space.serenity.scrollme.model.FlickrPhoto

/**
 * Created by karmamaker on 31/05/2017.
 */

class ImageBinder(parent: ViewGroup) : Binder(R.layout.item_image, parent) {
    override fun bind(data: Any) {
        if (data !is FlickrPhoto) return

        Picasso.with(itemView.context)
                .load("http://farm${data.farm}.static.flickr.com/${data.server}/${data.id}_${data.secret}.jpg")
                .error(R.drawable.abc_btn_colored_material)
                .resize(240, 240)
                .centerCrop()
                .into(itemView.img)
    }
}
