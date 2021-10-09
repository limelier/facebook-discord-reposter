package me.limelier.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

/**
 * A Facebook webhook payload containing
 * [feed changes](https://developers.facebook.com/docs/graph-api/webhooks/reference/page/#feed).
 */
public data class FeedEvent(
    @JsonProperty("object") val eventObject: String,
    @JsonProperty("entry") val entries: List<Entry>
) {
    public data class Entry(
        val id: String,
        val time: Int,
        val changes: List<Change>
    )

    public data class Change(
        val field: String, // relevant value: "feed"
        val value: Value,
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public data class Value(
            val verb: Verb,
            val post: PagePost?,
            val link: URL?, // attachment
            val message: String?, // text content
            val photo: URL?, // attached photo
            val video: URL?, // attached video
            val item: String, // type of post
        )

        @JsonIgnoreProperties(ignoreUnknown = true)
        public data class PagePost(
            @JsonProperty("is_published") val published: Boolean,
            @JsonProperty("permalink_url") val permalink: URL
        )
    }
}

