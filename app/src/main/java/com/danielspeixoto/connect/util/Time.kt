package com.danielspeixoto.connect.util

import java.sql.Date
import java.sql.Timestamp

/**
 * Created by danielspeixoto on 2/18/17.
 */

object Time {

    val DD_MM_YYYY = "dd/mm/yyyy"

    val todayInMillis: Long
        get() = getMillis(date)

    fun getDayInMillis(timestamp: Long): Long {
        return getMillis(Timestamp(timestamp).toString().substring(0, 10))
    }

    val date: String
        get() = Timestamp(System.currentTimeMillis()).toString().substring(0, 10)


    fun getMillis(date: String): Long {
        return Date.valueOf(date).time
    }

    fun getFormat(date: String, format: String): String {
        if (format == DD_MM_YYYY) {
            return date.substring(8) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4)
        }
        return date
    }

    fun convert(date: String, format: String): String {
        if (format == DD_MM_YYYY) {
            return date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2)
        }
        return date
    }
}
