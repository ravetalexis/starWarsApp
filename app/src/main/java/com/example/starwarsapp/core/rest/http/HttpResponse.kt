package fr.mhardy.kotlin_network.core.rest.http

import java.net.URL

sealed class HttpResponse<out T : Any>(
    url: URL,
    status: Int = -1,
    responseMessage: String = ""
) {
    data class Success<T : Any>(
        var value: T?,
        val url: URL,
        val status: Int = -1,
        val responseMessage: String = "",
        val contentLength: Long = 0L,
        val body: String = ""
    ) : HttpResponse<T>(url, status, responseMessage) {

        override fun toString(): String = buildString {
            appendln("<-- $status $url")
            appendln("Response: $responseMessage")
            appendln("Length: $contentLength")
            appendln("Body: $body")
        }
    }

    data class Error(
        val url: URL,
        val throwable: Throwable,
        val status: Int = -1,
        val responseMessage: String = ""
    ) : HttpResponse<Nothing>(url, status, responseMessage) {
        override fun toString(): String = buildString {
            appendln("<-- $status $url")
            appendln("Error: $throwable")
        }
    }
}