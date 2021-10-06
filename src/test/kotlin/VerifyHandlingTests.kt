import me.limelier.Config
import me.limelier.makeApp
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val client = OkHttpClient()

private class VerifyHandlingTests {
    private val app = makeApp()
    private val baseUrl = "http://localhost:${Config.Server.port}".toHttpUrl()

    private fun doGet(vararg queryParams: Pair<String, String>): Response {
        val builder = baseUrl.newBuilder()
        queryParams.forEach { builder.addQueryParameter(it.first, it.second) }
        val request = Request.Builder()
            .url(builder.build())
            .build()
        return client.newCall(request).execute()
    }

    @BeforeEach
    private fun setup() {
        app.start(Config.Server.port)
    }

    @Test
    fun `should respond with the challenge when given the right token`() {
        val response = doGet(
            "hub.type" to "subscribe",
            "hub.verify_token" to Config.Facebook.verifyToken,
            "hub.challenge" to "1234",
        )
        assertEquals(200, response.code)
        assertEquals("1234", response.body!!.string())
    }

    @Test
    fun `should respond with 401 UNAUTHORIZED if the token is wrong`() {
        val response = doGet(
            "hub.type" to "subscribe",
            "hub.verify_token" to "bad token",
        )
        assertEquals(401, response.code)
    }

    @AfterEach
    private fun tearDown() {
        app.stop()
    }
}
