package space.serenity.scrollme.model

import android.os.Parcel
import android.os.Parcelable

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

internal class SearchQuerySuggestion : SearchSuggestion {

    private var searchQuery: String = ""
    var isHistory = true

    constructor(givenSearchQuery: String) {
        this.searchQuery = givenSearchQuery.toLowerCase()
    }

    constructor(source: Parcel) {
        this.searchQuery = source.readString()
    }

    override fun getBody(): String {
        return searchQuery
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(searchQuery)
    }

    companion object {

        val CREATOR: Parcelable.Creator<SearchQuerySuggestion?> = object : Parcelable.Creator<SearchQuerySuggestion?> {
            override fun createFromParcel(parcel: Parcel): SearchQuerySuggestion {
                return SearchQuerySuggestion(parcel)
            }

            override fun newArray(size: Int): Array<SearchQuerySuggestion?> {
                return arrayOfNulls(size)
            }
        }
    }
}