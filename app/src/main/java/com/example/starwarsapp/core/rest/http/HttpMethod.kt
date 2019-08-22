package fr.mhardy.kotlin_network.core.rest.http

enum class HttpMethod(val verb: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");
}