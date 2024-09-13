package com.tws.moments.app.gsonadapters

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.tws.moments.data.source.api.tweet.TweetEntity
import java.lang.reflect.Type

class TweetDeserializer : JsonDeserializer<List<TweetEntity>> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<TweetEntity> {

        val tweetList = mutableListOf<TweetEntity>()

        val jsonArray: JsonArray = json.asJsonArray

        Log.d("TAGTS", json.toString())

        for (element in jsonArray) {
//            try {
            // Attempt to parse each element as a TweetEntity
            val tweetEntity = context.deserialize<TweetEntity>(element, TweetEntity::class.java)

            // Only add non-null TweetEntities to the list
            tweetEntity?.let { tweetList.add(it) }

//            } catch (e: JsonSyntaxException) {
//                // Handle parsing error - skip the invalid object
//                println("Skipping invalid tweet: ${e.message}")
//            } catch (e: JsonParseException) {
//                // Handle other parsing exceptions
//                println("Error parsing tweet: ${e.message}")
//            }
        }

        return tweetList

    }
}

