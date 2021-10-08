package me.limelier

import java.net.URL

public data class Config(
    val facebook: Facebook = Facebook(),
    val discord: Discord = Discord(),
    val server: Server = Server(),
) {
    public data class Facebook(
        val verifyToken: String = System.getenv("FACEBOOK_VERIFY_TOKEN")!!,
    )

    public data class Discord(
        val webhook: URL = URL(System.getenv("DISCORD_WEBHOOK_URL")!!),
        val attempts: Int = System.getenv("DISCORD_CALL_ATTEMPTS")?.toIntOrNull() ?: 5,
    )

    public data class Server(
        val port: Int = System.getenv("PORT")?.toIntOrNull() ?: 16842,
    )
}

