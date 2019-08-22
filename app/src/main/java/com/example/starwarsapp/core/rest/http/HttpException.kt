package fr.mhardy.kotlin_network.core.rest.http

class HttpException(httpCode: Int, httpMessage: String) : Exception("HTTP $httpCode: $httpMessage")