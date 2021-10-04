import dev.kord.common.entity.DiscordEmbed
import dev.kord.common.entity.optional.Optional
import exceptions.RequestFailedException
import model.DiscordReqBody
import model.Post
import mu.KotlinLogging
import util.post

private val logger = KotlinLogging.logger {}

fun sendPostToWebhook(post: Post) {
    val reqBody = DiscordReqBody(
        content = post.message,
        embeds = listOfNotNull(post.link, post.photoUrl, post.videoUrl)
            .map { DiscordEmbed(url = Optional(it.toString())) }
    )
    try {
        logger.info { "Sending post to Discord as object: $reqBody"}
        post(Config.Discord.webhook, reqBody, Config.Discord.attempts)
    } catch (e: RequestFailedException) {
        logger.error(e) { "Failed to send request to Discord webhook" }
    }
}