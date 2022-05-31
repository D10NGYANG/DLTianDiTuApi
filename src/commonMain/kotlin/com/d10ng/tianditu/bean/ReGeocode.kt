package com.d10ng.tianditu.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReGeocode(
    @SerialName("msg")
    var msg: String = "",
    @SerialName("result")
    var result: Result = Result(),
    @SerialName("status")
    var status: String = ""
) {
    @Serializable
    data class Result(
        @SerialName("addressComponent")
        var addressComponent: AddressComponent = AddressComponent(),
        @SerialName("formatted_address")
        var formattedAddress: String = "",
        @SerialName("location")
        var location: Location = Location()
    ) {
        @Serializable
        data class AddressComponent(
            @SerialName("address")
            var address: String = "",
            @SerialName("address_distance")
            var addressDistance: Int = 0,
            @SerialName("address_position")
            var addressPosition: String = "",
            @SerialName("city")
            var city: String = "",
            @SerialName("city_code")
            var cityCode: String = "",
            @SerialName("county")
            var county: String = "",
            @SerialName("county_code")
            var countyCode: String = "",
            @SerialName("nation")
            var nation: String = "",
            @SerialName("poi")
            var poi: String = "",
            @SerialName("poi_distance")
            var poiDistance: Int = 0,
            @SerialName("poi_position")
            var poiPosition: String = "",
            @SerialName("province")
            var province: String = "",
            @SerialName("province_code")
            var provinceCode: String = "",
            @SerialName("road")
            var road: String = "",
            @SerialName("road_distance")
            var roadDistance: Int = 0
        )

        @Serializable
        data class Location(
            @SerialName("lat")
            var lat: Double = 0.0,
            @SerialName("lon")
            var lon: Double = 0.0
        )
    }
}