package me.limelier

import com.fasterxml.jackson.databind.MapperFeature
import me.limelier.handlers.EventHandler
import me.limelier.handlers.VerifyHandler
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.plugin.json.JavalinJackson

public fun makeApp(): Javalin = Javalin
    .create { config ->
        config.jsonMapper(
            JavalinJackson(JavalinJackson.defaultMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS))
        )
    }.routes {
        get("/", VerifyHandler())
        post("/", EventHandler())
    }

private fun main() {
    makeApp().start(Config.Server.port)
}