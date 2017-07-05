package space.serenity.scrollme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.MyGridLayoutManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_launch.*
import space.serenity.scrollme.adapters.ImagesAdapter
import space.serenity.scrollme.providers.TestProvider
import space.serenity.scrollme.utils.GridSpacingItemDecoration


class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        // list
        list.layoutManager = MyGridLayoutManager(this, 2)
        list.addItemDecoration(GridSpacingItemDecoration(2, 10, false))

        val provider = TestProvider()

        val adapter = ImagesAdapter(provider)
        list.adapter = adapter

        provider.dataSetChangeListener = {
            refresh.isRefreshing = false
            adapter.notifyDataSetChanged()
        }
        refresh.setOnRefreshListener { provider.init() }

        provider.init()
    }
}
