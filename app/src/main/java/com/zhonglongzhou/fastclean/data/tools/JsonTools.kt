package com.zhonglongzhou.fastclean.data.tools

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.Instant
import org.joda.time.format.DateTimeFormatterBuilder
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type

/**
 * Created by zhonglz on 2018/5/4
 */
object JsonTools {
    private val gson: Gson by lazy {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(DateTime::class.java, DateTimeTypeConverter())
        builder.registerTypeAdapter(Instant::class.java, InstantTypeConverter())
        builder.create()
    }

    @Throws(Throwable::class)
    fun <T> fromJson(json: String?, type: Type): T = gson.fromJson<T>(json, type)

    @Throws(Throwable::class)
    fun toJson(obj: Any?): String? = if (obj == null) {
                                         null
                                     } else {
                                         gson.toJson(obj)
                                     }

    private class DateTimeTypeConverter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
        override fun serialize(src: DateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
                JsonPrimitive(src?.toString())

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime {
            val dateTimeFormatter = DateTimeFormatterBuilder()
                    .append(ISODateTimeFormat.dateTimeParser())
                    .toFormatter()
            return DateTime.parse(json?.asString, dateTimeFormatter)
        }

    }

    private class InstantTypeConverter : JsonSerializer<Instant>, JsonDeserializer<Instant> {
        override fun serialize(src: Instant?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
                JsonPrimitive(src?.millis)

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Instant =
                Instant(json?.asString)

    }
}