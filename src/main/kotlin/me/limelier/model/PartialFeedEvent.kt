package me.limelier.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URL

/**
 * A subset of the fields included in a
 * [Facebook page webhook feed event](https://developers.facebook.com/docs/graph-api/webhooks/reference/page/#feed),
 * containing only what we need.
 */
public data class PartialFeedEvent(
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

