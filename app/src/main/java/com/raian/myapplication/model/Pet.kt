package com.raian.myapplication.model

import java.io.Serializable

data class Pet(
    val id: Int,
    val title: String,
    val sex: String,
    val age: Int,
    val description: String,
    val puppyImageId: Int = 0
): Serializable