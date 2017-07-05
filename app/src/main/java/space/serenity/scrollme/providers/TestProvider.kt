package space.serenity.scrollme.providers

/**
 * Created by karmamaker on 05/07/2017.
 */

class TestProvider : Provider {
    constructor() : super()


    override fun init() {
        source.clear()
        source.addAll(listOf<Any>(ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL,
                ITEM_LOADING_SMALL))

        notifyDataSetChanged()
    }
}