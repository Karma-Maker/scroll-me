package space.serenity.scrollme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.MyGridLayoutManager
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.synthetic.main.activity_launch.*
import space.serenity.scrollme.adapters.ImagesAdapter
import space.serenity.scrollme.model.SearchQuerySuggestion
import space.serenity.scrollme.providers.ImagesProvider
import space.serenity.scrollme.utils.GridSpacingItemDecoration
import space.serenity.scrollme.utils.Suggestions


class LaunchActivity : AppCompatActivity() {

    val suggestions = Suggestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        // list
        list.layoutManager = MyGridLayoutManager(this, 2)
        list.addItemDecoration(GridSpacingItemDecoration(2, 10, false))

        val provider = ImagesProvider()

        val adapter = ImagesAdapter(provider)
        list.adapter = adapter

        provider.dataSetChangeListener = {
            refresh.isRefreshing = false
            suggestions.putSearchQuery(provider.searchQuery)
            adapter.notifyDataSetChanged()
        }
        refresh.setOnRefreshListener { provider.init() }

        imageSearch.setOnQueryChangeListener { oldQuery, newQuery -> run {
            provider.searchQuery = newQuery
            imageSearch.swapSuggestions(suggestions.getSuggestion(provider.searchQuery, 3).map { suggestion -> SearchQuerySuggestion(suggestion) })
        }}

        imageSearch.setOnFocusChangeListener(object : FloatingSearchView.OnFocusChangeListener {
            override fun onFocus() {
                imageSearch.swapSuggestions(suggestions.getSuggestion(provider.searchQuery, 3).map { suggestion -> SearchQuerySuggestion(suggestion) })
            }

            override fun onFocusCleared() {
            }
        })


        imageSearch.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSuggestionClicked(suggestion: SearchSuggestion) {
                val searchSuggestion = suggestion as SearchQuerySuggestion
                imageSearch.setSearchText(searchSuggestion.body)
                suggestions.putSearchQuery(searchSuggestion.body)
                imageSearch.swapSuggestions(listOf())
            }

            override fun onSearchAction(query: String) {
                provider.searchQuery = query
            }
        })

        provider.init()
    }

    class SimpleSearchSugges
}
