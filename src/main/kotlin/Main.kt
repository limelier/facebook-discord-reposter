import com.fasterxml.jackson.databind.MapperFeature
import handlers.EventHandler
import handlers.VerifyHandler
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import mu.KotlinLogging

private val logger = KotlinLogging.logger("test")

fun main() {
    val app = Javalin.create { config ->
        config.jsonMapper(
            JavalinJackson(JavalinJackson.defaultMapper().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS))
        )
    }.start(Config.Server.port)

    app.get("/", VerifyHandler())
    app.post("/", EventHandler())
}