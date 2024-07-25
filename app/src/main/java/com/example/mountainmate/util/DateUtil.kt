package com.example.mountainmate.util

import android.text.format.DateFormat
import java.util.Calendar
import java.util.Locale

object DateUtil {

    private const val DATE_TIME_FORMAT_SLASH = "yyyy/MM/dd HH:mm:ss"

    /**
     * convert timestamp to string format. ex. yyyy/MM/dd HH:mm
     *
     * @param timeStamp long
     * @return dateString
     */
    fun convertTimeStampToStringWithYearAndTime(timeStamp: Long): String {
        val cal = Calendar.getInstance(Locale.TAIWAN).apply {
            timeInMillis = timeStamp
        }
        return DateFormat.format(DATE_TIME_FORMAT_SLASH, cal).toString()
    }

}