package me.limelier

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import io.javalin.Javalin
import me.limelier.handlers.EventHandler
import me.limelier.handlers.VerifyHandler
import io.javalin.plugin.json.JavalinJackson

public fun makeApp(config: Config): Javalin = Javalin
    .create { cfg ->
        cfg.jsonMapper(JavalinJackson(
            JavalinJackson
                .defaultMapper()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        ))
    }
    .get("/", VerifyHandler(config))
    .post("/", EventHandler(config))

private fun main() {
    val config = Config()
    makeApp(config).start(config.server.port)
}