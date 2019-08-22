package com.example.starwarsapp.utils

import org.json.JSONArray
import org.json.JSONObject

/**
 * Transforms a [JSONArray] to a [List] of [JSONObject].
 * @param jsonArray The array to transform into List.
 */
internal fun jsonToList(jsonArray: JSONArray): List<JSONObject>? =
    jsonArray.let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }

/**
 * Retrieve "data" array from a json as String.
 * @param json The json as String.
 */
internal fun dataArrayFromJson(json: String): JSONArray? =
    JSONArray(json)

/**
 * Retrieve "data" object from a json as String.
 * @param json The json as String.
 */
internal fun dataObjectFromJson(json: String): JSONObject? =
    JSONObject(json).getJSONObject("data")