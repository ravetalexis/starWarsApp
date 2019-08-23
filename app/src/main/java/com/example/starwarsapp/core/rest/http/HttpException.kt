package com.example.starwarsapp.core.rest.http

class HttpException(httpCode: Int, httpMessage: String) : Exception("HTTP $httpCode: $httpMessage")