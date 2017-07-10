package space.serenity.scrollme.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import io.paperdb.Paper
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by karmamaker on 06/07/2017.
 */

class Suggestions: LifecycleObserver {
    val lifecycleRegistry: LifecycleRegistry

    constructor(lifecycleRegistry: LifecycleRegistry){
        this.lifecycleRegistry = lifecycleRegistry
        lifecycleRegistry.addObserver(this)
    }

    var suggestions = ArrayList<String>()


    fun getSuggestion(givenQuery: String, size: Int): List<String> {
        var result = ArrayList<String>(size)

        for(searchQueryInCache in suggestions) {
            if(searchQueryInCache.startsWith(givenQuery)){
                result.add(searchQueryInCache)
                if(result.count() == size){
                    return result
                }
            }
        }
        return result
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START) fun restoreSuggestions(){
        suggestions.clear()
        suggestions.addAll(Paper.book("suggestions").read("suggestions", listOf<String>())) // Primitive
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP) fun saveSuggestions(){
        Paper.book("suggestions").write("suggestions", suggestions)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) fun unsubscribe(){
        lifecycleRegistry.removeObserver(this)
    }

    fun putSearchQuery(searchQuery: String){
        if(searchQuery.isEmpty()){
            return
        }
        suggestions.remove(searchQuery)
        suggestions.add(0, searchQuery)
    }
}