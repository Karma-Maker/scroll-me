package space.serenity.scrollme.adapters

/**
 * Created by karmamaker on 05/07/2017.
 */

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import space.serenity.scrollme.R
import space.serenity.scrollme.binders.Binder
import space.serenity.scrollme.binders.DummyBinder
import space.serenity.scrollme.binders.ImageBinder
import space.serenity.scrollme.providers.ITEM_LOADING_SMALL
import space.serenity.scrollme.providers.Provider

class ImagesAdapter(val provider : Provider) : RecyclerView.Adapter<Binder>() {
    override fun getItemViewType(position: Int): Int {
        val item = provider.get(position)
        when (item){
            ITEM_LOADING_SMALL -> return 1
            else -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Binder {
        when (viewType){
            0 -> return ImageBinder(parent)
            else -> return DummyBinder(R.layout.item_loading_small, parent)
        }
    }


    override fun onBindViewHolder(holder: Binder, position: Int) {
        holder.bind(provider.get(position))
    }

    override fun getItemCount(): Int {
        return provider.count
    }
}

