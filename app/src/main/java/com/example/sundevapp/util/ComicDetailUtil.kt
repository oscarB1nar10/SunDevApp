package com.example.sundevapp.util

class ComicDetailUtil {

    companion object{

        @JvmStatic
         fun getComicDetailString(comicDetail: String): String{
            val index = comicDetail.indexOf("issue")
            val lastAppearanceOfSlash = comicDetail.lastIndexOf("/")
            return comicDetail.substring(index+6, lastAppearanceOfSlash)
        }
    }
}