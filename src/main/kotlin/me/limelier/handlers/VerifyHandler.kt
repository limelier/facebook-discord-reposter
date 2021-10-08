package me.limelier.handlers

import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import me.limelier.Config
import org.apache.logging.log4j.kotlin.Logging

public class VerifyHandler(private val config: Config) : Handler {
    private companion object : Logging

    override fun handle(ctx: Context) {
        logger.info { "Received verify request from ${ctx.ip()}" }
        ctx.queryParamAsClass<String>("hub.mode")
            .check({ it == "subscribe" }, "hub.mode has to be 'subscribe'")
            .get()

        val verifyToken = ctx.queryParamAsClass<String>("hub.verify_token").get()

        if (verifyToken != config.facebook.verifyToken) throw UnauthorizedResponse()

        val challenge = ctx.queryParamAsClass<Int>("hub.challenge").get()

        logger.info { "Accepted verify request from ${ctx.ip()}" }
        ctx.status(200)
        ctx.result(challenge.toString())
    }
}