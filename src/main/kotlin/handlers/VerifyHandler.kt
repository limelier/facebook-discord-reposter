package handlers

import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class VerifyHandler : Handler {
    override fun handle(ctx: Context) {
        logger.info { "Received verify request from ${ctx.ip()}" }
        ctx.queryParamAsClass<String>("hub.type")
            .check({ it == "subscribe" }, "hub.type has to be 'subscribe'")
            .get()

        val verifyToken = ctx.queryParamAsClass<String>("hub.verify_token").get()

        if (verifyToken != Config.Facebook.verifyToken) throw UnauthorizedResponse()

        val challenge = ctx.queryParamAsClass<Int>("hub.challenge").get()

        logger.info { "Accepted verify request from ${ctx.ip()}" }
        ctx.status(200)
        ctx.result(challenge.toString())
    }
}