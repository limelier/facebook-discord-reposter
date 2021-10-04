package me.limelier.model

import dev.kord.common.entity.DiscordEmbed

public data class DiscordReqBody(
    val content: String?,
    val embeds: List<DiscordEmbed>,
)