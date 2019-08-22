package fr.mhardy.kotlin_network.core.rest.http

import android.util.Log
import fr.mhardy.kotlin_network.BuildConfig
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection

class HttpClient {

    companion object {
        private const val TAG = "HttpClient"
        private const val DEFAULT_TIMEOUT = 30_000
    }

    fun <T : Any>executeRequest(request: HttpRequest): HttpResponse<T> {
        if (BuildConfig.DEBUG) Log.d(TAG, request.toString())
        return runCatching { sendRequest<T>(request) }.fold(
            onSuccess = { it.also { if (BuildConfig.DEBUG) Log.d(TAG, it.toString()) } },
            onFailure = { throwable ->
                HttpResponse.Error(request.url, throwable).also { if (BuildConfig.DEBUG) Log.d(
                    TAG, it.toString()) }
            }
        )
    }

    private fun <T : Any>sendRequest(request: HttpRequest): HttpResponse<T> {
        val connection = establishConnection(request) as HttpURLConnection
        connection.apply {
            connectTimeout = DEFAULT_TIMEOUT
            readTimeout = DEFAULT_TIMEOUT
            requestMethod = request.method.verb
            doInput = true
            useCaches = true
            instanceFollowRedirects = true

            request.headers.forEach { (key, value) ->
                setRequestProperty(key, value)
            }

            setDoOutput(connection, request.method)
            setBody(connection, request)
        }
        return retrieveResponse(request, connection)
    }


    private fun establishConnection(request: HttpRequest): URLConnection {
        val urlConnection = request.url.openConnection()
        return if (request.url.protocol == "https") {
            (urlConnection as HttpsURLConnection).apply {
                sslSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory()
                hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()
            }
        } else {
            urlConnection as HttpURLConnection
        }
    }

    private fun setDoOutput(connection: URLConnection, method: HttpMethod) = when (method) {
        HttpMethod.GET -> connection.doOutput = false
        HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE -> connection.doOutput = true
    }

    private fun setBody(connection: URLConnection, request: HttpRequest) {
        if (!connection.doInput || request.parameters.isEmpty()) return

        DataOutputStream(connection.outputStream).use { writer ->
            writer.writeBytes(request.parameters.toString())
            writer.flush()
        }
    }

    private fun <T : Any>retrieveResponse(request: HttpRequest, connection: HttpURLConnection): HttpResponse<T> {
        if (connection.responseCode !in 200 until 300) {
            return HttpResponse.Error(
                request.url,
                HttpException(
                    connection.responseCode,
                    connection.responseMessage.orEmpty()
                ),
                connection.responseCode,
                connection.responseMessage
            )
        }
        connection.inputStream.use { inputStream ->
            inputStream.bufferedReader().use { bufferedReader ->
                val content = bufferedReader.readText()
                return HttpResponse.Success(
                    null,
                    request.url,
                    connection.responseCode,
                    connection.responseMessage.orEmpty(),
                    content.length.toLong(),
                    content
                )
            }
        }
    }
}