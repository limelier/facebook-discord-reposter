import java.net.URL

object Config {
    object Facebook {
        val verifyToken = System.getenv("FACEBOOK_VERIFY_TOKEN")!!
    }

    object Discord {
        val webhook = URL(System.getenv("DISCORD_WEBHOOK_URL")!!)
        val attempts = System.getenv("DISCORD_CALL_ATTEMPTS")?.toInt() ?: 5
    }

    object Server {
        val port = System.getenv("PORT")?.toInt() ?: 16842
    }
}