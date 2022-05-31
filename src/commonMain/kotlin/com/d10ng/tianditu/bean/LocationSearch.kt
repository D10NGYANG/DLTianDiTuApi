package com.d10ng.tianditu.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationSearch(
    @SerialName("area")
    var area: Area = Area(),
    @SerialName("count")
    var count: String = "",
    @SerialName("keyWord")
    var keyWord: String = "",
    @SerialName("lineData")
    var lineData: List<LineData> = listOf(),
    @SerialName("pois")
    var pois: List<Poi> = listOf(),
    @SerialName("prompt")
    var prompt: List<Prompt> = listOf(),
    @SerialName("statistics")
    var statistics: Statistics = Statistics(),
    @SerialName("resultType")
    var resultType: Int = 0,
    @SerialName("status")
    var status: Status = Status()
) {
    @Serializable
    data class Area(
        @SerialName("adminCode")
        var adminCode: Int = 0,
        @SerialName("bound")
        var bound: String = "",
        @SerialName("level")
        var level: String = "",
        @SerialName("lonlat")
        var lonlat: String = "",
        @SerialName("name")
        var name: String = ""
    )

    @Serializable
    data class LineData(
        @SerialName("stationNum")
        var stationNum: String = "",
        @SerialName("poiType")
        var poiType: String = "",
        @SerialName("name")
        var name: String = "",
        @SerialName("uuid")
        var uuid: String = ""
    )

    @Serializable
    data class Poi(
        @SerialName("address")
        var address: String = "",
        @SerialName("hotPointID")
        var hotPointID: String = "",
        @SerialName("lonlat")
        var lonlat: String = "",
        @SerialName("name")
        var name: String = "",
        @SerialName("phone")
        var phone: String = "",
        @SerialName("poiType")
        var poiType: String = "",
        @SerialName("source")
        var source: String = "",
        @SerialName("stationData")
        var stationData: List<StationData> = listOf()
    ) {
        @Serializable
        data class StationData(
            @SerialName("lineName")
            var lineName: String = "",
            @SerialName("stationUuid")
            var stationUuid: String = "",
            @SerialName("uuid")
            var uuid: String = ""
        )
    }

    @Serializable
    data class Prompt(
        @SerialName("admins")
        var admins: List<Admin> = listOf(),
        @SerialName("type")
        var type: Int = 0
    ) {
        @Serializable
        data class Admin(
            @SerialName("adminCode")
            var adminCode: Int = 0,
            @SerialName("adminName")
            var adminName: String = ""
        )
    }

    @Serializable
    data class Statistics(
        @SerialName("adminCount")
        var adminCount: Int = 0,
        @SerialName("allAdmins")
        var allAdmins: List<AllAdmin> = listOf(),
        @SerialName("keyword")
        var keyword: String = "",
        @SerialName("priorityCitys")
        var priorityCitys: List<PriorityCity> = listOf()
    ) {
        @Serializable
        data class AllAdmin(
            @SerialName("adminCode")
            var adminCode: Int = 0,
            @SerialName("adminName")
            var adminName: String = "",
            @SerialName("count")
            var count: Int = 0,
            @SerialName("ename")
            var ename: String = "",
            @SerialName("isleaf")
            var isleaf: Boolean = false,
            @SerialName("lonlat")
            var lonlat: String = ""
        )

        @Serializable
        data class PriorityCity(
            @SerialName("adminCode")
            var adminCode: Int = 0,
            @SerialName("adminName")
            var adminName: String = "",
            @SerialName("count")
            var count: String = "",
            @SerialName("ename")
            var ename: String = "",
            @SerialName("lonlat")
            var lonlat: String = ""
        )
    }

    @Serializable
    data class Status(
        @SerialName("cndesc")
        var cndesc: String = "",
        @SerialName("infocode")
        var infocode: Int = 0
    )
}