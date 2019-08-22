package fr.mhardy.kotlin_network.core.rest.http

import java.net.URL

typealias Headers = Map<String, String>
typealias Parameters = Map<String, Any>

class HttpRequest(
    val method: HttpMethod,
    val url: URL,
    val headers: Headers = DEFAULT_JSON_HEADER,
    val parameters: Parameters = mapOf()
) {
    companion object {
        private val DEFAULT_JSON_HEADER = mapOf("Content-Type" to "application/json")
    }

    override fun toString(): String = buildString {
        appendln("--> $method $url")
        appendln("Headers: $headers")
        appendln("Body: $parameters")
    }
}