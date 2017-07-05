package space.serenity.scrollme.providers

/**
 * Created by karmamaker on 05/07/2017.
 */

abstract class Provider {
    var dataSetChangeListener: () -> Unit = {}

    abstract fun init()

    operator fun get(position: Int): Any {
        return source[position]
    }

    val count: Int get() = source.size

    protected val source = ArrayList<Any>()
    protected fun notifyDataSetChanged(){
        dataSetChangeListener.invoke();
    }
}