package space.serenity.scrollme.providers

/**
 * Created by karmamaker on 05/07/2017.
 */

abstract class Provider {
    var dataInsertListener: (start: Int, count: Int) -> Unit = {start, count -> }
    var dataRemovedListener: (start: Int, count: Int) -> Unit = {start, count -> }

    abstract fun init()

    open operator fun get(position: Int): Any {
        return source[position]
    }

    val count: Int get() = source.size

    protected val source = ArrayList<Any>()
    protected fun notifyDataInsert(start: Int, count: Int){
        dataInsertListener.invoke(start, count);
    }

    protected fun notifyDataRemove(start: Int, count: Int){
        dataRemovedListener.invoke(start, count);
    }
}