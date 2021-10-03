package model

import java.net.URL

/** A Facebook page post. */
data class Post(
    val postUrl: URL?,
    val message: String?,
    val link: URL?,
    val photoUrl: URL?,
    val videoUrl: URL?,
) {
    companion object {
        fun fromEventValue(value: PartialFeedEvent.Value): Post = with(value) {
            Post(post?.permalink, message, link, photo, video)
        }
    }
}


