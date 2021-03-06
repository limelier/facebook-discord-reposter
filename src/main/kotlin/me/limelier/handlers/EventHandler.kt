package me.limelier.handlers

import io.javalin.http.Context
import io.javalin.http.Handler
import me.limelier.Config
import me.limelier.DiscordSender
import me.limelier.model.FeedEvent
import me.limelier.model.Post
import me.limelier.model.Verb
import org.apache.logging.log4j.kotlin.Logging

public class EventHandler(config: Config) : Handler {
    private companion object : Logging

    private val discordSender = DiscordSender(config)

    override fun handle(ctx: Context) {
        val partialFeedEvent = ctx.bodyValidator<FeedEvent>()
            .check({ it.eventObject == "page" }, "'object' must be 'page'")
            .get()

        val (eventObject, entries) = partialFeedEvent
        logger.info("Received $eventObject event from ${ctx.ip()}")
        logger.debug { partialFeedEvent }

        for (entry in entries) with(entry) {
            logger.info("Handling entry $id with timestamp $time")
            for (change in changes) with(change) {
                if (field != "feed") {
                    logger.info { "Change is on field $field, ignoring" }
                    return
                }
                if (value.verb != Verb.ADD) {
                    logger.info { "Verb is not ${Verb.ADD}, ignoring" }
                    return
                }

                val post = Post.fromEventValue(value)
                logger.info { "Constructed post object: $post" }

                discordSender.sendPostToWebhook(post)
            }
        }
    }
}