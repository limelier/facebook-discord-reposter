package me.limelier

import com.fasterxml.jackson.databind.MapperFeature
import me.limelier.handlers.EventHandler
import me.limelier.handlers.VerifyHandler
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import org.apache.logging.log4j.kotlin.logger

private fun main() {
    val app = Javalin.create { config ->
        config.jsonMapper(
            JavalinJackson(JavalinJackson.defaultMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS))
        )
    }.start(Config.Server.port)

    app.get("/", VerifyHandler())
    app.post("/", EventHandler())
}