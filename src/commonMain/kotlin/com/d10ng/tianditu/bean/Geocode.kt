package com.d10ng.tianditu.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geocode(
    @SerialName("location")
    var location: Location = Location(),
    @SerialName("msg")
    var msg: String = "",
    @SerialName("searchVersion")
    var searchVersion: String = "",
    @SerialName("status")
    var status: String = ""
) {
    @Serializable
    data class Location(
        @SerialName("keyWord")
        var keyWord: String = "",
        @SerialName("lat")
        var lat: Double = 0.0,
        @SerialName("level")
        var level: String = "",
        @SerialName("lon")
        var lon: Double = 0.0,
        @SerialName("score")
        var score: Int = 0
    )
}