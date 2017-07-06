package space.serenity.scrollme.utils

import android.util.LruCache
import java.util.*


/**
 * Created by karmamaker on 06/07/2017.
 */

class Suggestions{
    var cache = Stack<String>()
    fun getSuggestion(givenQuery: String, size: Int): List<String> {
        var result = ArrayList<String>(size)

        for(searchQueryInCache in cache) {
            if(searchQueryInCache.startsWith(givenQuery)){
                result.add(searchQueryInCache)
                if(result.count() == size){
                    return result
                }
            }
        }
        return result
    }

    fun putSearchQuery(searchQuery: String){
        if(searchQuery.isEmpty()){
            return
        }
        cache.remove(searchQuery)
        cache.push(searchQuery)
    }
}