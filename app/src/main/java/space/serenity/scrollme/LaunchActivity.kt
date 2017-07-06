package space.serenity.scrollme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.MyGridLayoutManager
import kotlinx.android.synthetic.main.activity_launch.*
import space.serenity.scrollme.adapters.ImagesAdapter
import space.serenity.scrollme.providers.ImagesProvider
import space.serenity.scrollme.utils.GridSpacingItemDecoration


class LaunchActivity : AppCompatActivity() {

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
            adapter.notifyDataSetChanged()
        }
        refresh.setOnRefreshListener { provider.init() }

        imageSearch.setOnQueryChangeListener { oldQuery, newQuery -> provider.searchQuery = newQuery}
        provider.init()
    }
}
