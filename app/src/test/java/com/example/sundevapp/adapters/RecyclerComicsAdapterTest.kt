package com.example.sundevapp.adapters

import com.example.sundevapp.util.DateUtil
import org.junit.Assert
import org.junit.jupiter.api.Test

internal class RecyclerComicsAdapterTest{

    @Test
    fun dateInCustomFormat_isEquals_ToSpecificDate(){
        val date = "2019-06-06 11:10:16"
        val resultCustomDate = DateUtil.getDateInCustomFormat(date)
        Assert.assertEquals("Jun 06, 2019",resultCustomDate )
    }



}