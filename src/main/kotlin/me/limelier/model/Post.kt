package me.limelier.model

import java.net.URL

/** A Facebook page post. */
public data class Post(
    val postUrl: URL?,
    val message: String?,
    val link: URL?,
    val photoUrl: URL?,
    val videoUrl: URL?,
) {
    public companion object {
        public fun fromEventValue(value: FeedEvent.Change.Value): Post = with(value) {
            Post(post?.permalink, message, link, photo, video)
        }
    }
}


