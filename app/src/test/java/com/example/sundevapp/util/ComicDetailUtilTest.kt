package com.example.sundevapp.util

import org.junit.Assert
import org.junit.Test

internal class ComicDetailUtilTest{

    @Test
    fun comicDetail_generalUrl_assertTrue(){
        val apiDetailUrl = "https://comicvine.gamespot.com/api/issue/4000-6/"
        val generatedDetail = ComicDetailUtil.getComicDetailString(apiDetailUrl)
        Assert.assertEquals("4000-6",generatedDetail )
    }
}