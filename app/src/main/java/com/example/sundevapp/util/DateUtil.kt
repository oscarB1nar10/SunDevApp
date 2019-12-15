package com.example.sundevapp.util

import java.util.*

class DateUtil {

    companion object{

        @JvmStatic
        fun getDateInCustomFormat(dateString: String): String{
            val latsIndexInDate =  (dateString.indexOf(" ") )
            val stringTokenizer = StringTokenizer(dateString.substring(0,latsIndexInDate), "-")
            var iterator = 0
            var year = ""
            var moth = ""
            var day = ""
            while (stringTokenizer.hasMoreTokens()){
                iterator++
                when(iterator){
                    1 -> {year = stringTokenizer.nextToken() }
                    2 -> {moth = getMonthName(stringTokenizer.nextToken()) }
                    3 -> {day = stringTokenizer.nextToken() }
                }
            }
            return  "$moth $day, $year"//October 03, 2018
        }

        private fun getMonthName(month: String): String{
            return when(month){
                "01" -> {
                    "JANUARY"
                }
                "02" -> {
                    "February"
                }
                "03" -> {
                    "March"
                }
                "04" -> {
                    "April"
                }
                "05" -> {
                    "May"
                }
                "06" -> {
                    "Jun"
                }
                "07" -> {
                    "July"
                }
                "08" -> {
                    "August"
                }
                "09" -> {
                    "September"
                }
                "10" -> {
                    "October"
                }
                "11" -> {
                    "November"
                }
                "12" -> {
                    "December"
                }
                else -> {
                    ""
                }
            }
        }
    }
}