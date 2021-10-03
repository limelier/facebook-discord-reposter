package util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import exceptions.RequestFailedException
import mu.KotlinLogging
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.net.URL

private val logger = KotlinLogging.logger {}
private val mapper = jacksonObjectMapper()
private val client = OkHttpClient()
private val json = "application/json; charset=utf-8".toMediaType()

/**
 * Send a JSON POST request.
 *
 * @param url the URL to send the request to
 * @param body will be converted to JSON for the request body
 * @param attempts how many times to attempt the POST; must be at least 1
 *
 * @throws RequestFailedException request failed after `attempts` attempts
 */
fun post(url: URL, body: Any, attempts: Int) {
    assert(attempts >= 1)
    val jsonBody = mapper.writeValueAsString(body)
    val request = Request.Builder()
        .url(url)
        .post(jsonBody.toRequestBody(json))
        .build()

    for (attempt in 1..attempts) {
        client.newCall(request).execute().use { response ->
            if (response.isSuccessful) return
            logger.warn { "Request to $url failed with $response, ${attempts - attempt} attempts remaining..." }
        }
    }

    throw RequestFailedException("Request to $url with $jsonBody failed after $attempts attempts")
}