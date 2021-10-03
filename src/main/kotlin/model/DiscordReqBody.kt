package model

import dev.kord.common.entity.DiscordEmbed

data class DiscordReqBody(
    val content: String?,
    val embeds: List<DiscordEmbed>,
)