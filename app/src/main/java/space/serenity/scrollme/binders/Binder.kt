package space.serenity.scrollme.binders
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by karmamaker on 05/07/2017.
 */

abstract class Binder(layoutId: Int, parent: ViewGroup)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {
    abstract fun bind(data: Any)
}