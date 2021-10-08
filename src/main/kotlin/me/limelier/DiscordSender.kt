package me.limelier

import dev.kord.common.entity.DiscordEmbed
import dev.kord.common.entity.optional.Optional
import me.limelier.exceptions.RequestFailedException
import me.limelier.model.DiscordReqBody
import me.limelier.model.Post
import org.apache.logging.log4j.kotlin.logger
import org.apache.logging.log4j.kotlin.contextName
import me.limelier.util.post

private val logger = logger(contextName { })

public class DiscordSender(private val config: Config) {
    public fun sendPostToWebhook(post: Post) {
        val reqBody = DiscordReqBody(
            content = post.message,
            embeds = listOfNotNull(post.link, post.photoUrl, post.videoUrl)
                .map { DiscordEmbed(url = Optional(it.toString())) }
        )
        try {
            logger.info { "Sending post to Discord as object: $reqBody" }
            post(config.discord.webhook, reqBody, config.discord.attempts)
        } catch (e: RequestFailedException) {
            logger.error(e) { "Failed to send request to Discord webhook" }
        }
    }
}