package handlers

import io.javalin.http.Context
import io.javalin.http.Handler
import model.PartialFeedEvent
import model.Post
import model.Verb
import mu.KotlinLogging
import sendPostToWebhook

private val logger = KotlinLogging.logger {}

class EventHandler : Handler {
    override fun handle(ctx: Context) {
        val partialFeedEvent = ctx.bodyValidator<PartialFeedEvent>().get()
        val (field, value) = partialFeedEvent
        logger.info { "Received $field event from ${ctx.ip()}" }
        logger.debug { partialFeedEvent }

        if (field != "feed") {
            logger.info { "Event is on field $field, ignoring" }
            return
        }
        if (value.verb != Verb.ADD) {
            logger.info { "Verb is not ${Verb.ADD}, ignoring" }
            return
        }

        val post = Post.fromEventValue(value)
        logger.info { "Constructed post object: $post"}

        sendPostToWebhook(post)
    }
}