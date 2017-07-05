package space.serenity.scrollme.binders

import android.graphics.Color
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import space.serenity.scrollme.R

/**
 * Created by karmamaker on 31/05/2017.
 */

class ImageBinder(parent: ViewGroup) : Binder(R.layout.item_image, parent) {
    override fun bind(data: Any) {

        Picasso.with(itemView.context)
                .load(data as String)
                .error(R.drawable.abc_btn_colored_material)
                .resize(240, 240)
                .centerCrop()
                .into(itemView.img)
    }
}
