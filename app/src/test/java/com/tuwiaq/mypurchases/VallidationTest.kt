package com.tuwiaq.mypurchases


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class VallidationTest{
    private lateinit var vallidation: Vallidation

    @Before
    fun setUp(){
        vallidation= Vallidation()
    }
    @Test
    fun  checkEmaill()
    {
        val  result=vallidation.email("test123@gmail.com")
        assertThat(result).isTrue()
    }
}