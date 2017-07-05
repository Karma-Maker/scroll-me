package space.serenity.scrollme.adapters

/**
 * Created by karmamaker on 05/07/2017.
 */

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import space.serenity.scrollme.R
import space.serenity.scrollme.binders.Binder
import space.serenity.scrollme.binders.DummyBinder
import space.serenity.scrollme.providers.Provider

class ImagesAdapter(val provider : Provider) : RecyclerView.Adapter<Binder>() {
    override fun getItemViewType(position: Int): Int {
        val item = provider.get(position)
        when (item){
            else -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Binder {
        when (viewType){
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

