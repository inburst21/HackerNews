package inburst.com.hackernews.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import inburst.com.hackernews.models.Source


/**
 *  lennyhicks
 *  2/21/19
 */
class Converters {
    @TypeConverter
    fun fromString(value: String): Source {
        val source = object : TypeToken<Source>() {

        }.type
        return Gson().fromJson(value, source)
    }

    @TypeConverter
    fun fromSource(source: Source): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}