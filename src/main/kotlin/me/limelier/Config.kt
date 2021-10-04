package me.limelier

import java.net.URL

public object Config {
    public object Facebook {
        public val verifyToken: String = System.getenv("FACEBOOK_VERIFY_TOKEN")!!
    }

    public object Discord {
        public val webhook: URL = URL(System.getenv("DISCORD_WEBHOOK_URL")!!)
        public val attempts: Int = System.getenv("DISCORD_CALL_ATTEMPTS")?.toInt() ?: 5
    }

    public object Server {
        public val port: Int = System.getenv("PORT")?.toInt() ?: 16842
    }
}