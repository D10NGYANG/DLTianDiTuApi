package com.d10ng.tianditu.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PerimeterSearch(
    // 返回总条数
    @SerialName("count")
    var count: String = "",
    // 搜索关键词
    @SerialName("keyWord")
    var keyWord: String = "",
    // 针对点（类型1）集合返回
    @SerialName("pois")
    var pois: List<Poi> = listOf(),
    // 返回结果类型，取值1-5，对应不同的响应类型： 1（普通POI），2（统计），3（行政区)，4（建议词搜索），5（线路结果）
    @SerialName("resultType")
    var resultType: Int = 0,
    // 返回状态信息
    @SerialName("status")
    var status: Status = Status()
) {
    @Serializable
    data class Poi(
        // 地址
        @SerialName("address")
        var address: String = "",
        // 英文地址
        @SerialName("eaddress")
        var eaddress: String = "",
        // 距离（单位 m,km），1千米以下单位为米（m），1千米以上单位为千米（km）
        @SerialName("distance")
        var distance: String = "",
        // poi热点ID
        @SerialName("hotPointID")
        var hotPointID: String = "",
        // Poi点经纬度，格式：经度,纬度
        @SerialName("lonlat")
        var lonlat: String = "",
        // Poi点名称
        @SerialName("name")
        var name: String = "",
        // 英文名称
        @SerialName("ename")
        var ename: String = "",
        // 电话
        @SerialName("phone")
        var phone: String = "",
        // poi类型，101:POI数据 102:公交站点
        @SerialName("poiType")
        var poiType: String = "",
        // 数据信息来源
        @SerialName("source")
        var source: String = "",
        // 所属省名称
        @SerialName("province")
        var province: String = "",
        // 省行政区编码
        @SerialName("provinceCode")
        var provinceCode: String = "",
        // 所属城市名称
        @SerialName("city")
        var city: String = "",
        // 市行政区编码
        @SerialName("cityCode")
        var cityCode: String = "",
        // 所属区县名称
        @SerialName("county")
        var county: String = "",
        // 区县行政区编码
        @SerialName("countyCode")
        var countyCode: String = "",
        // 分类编码
        @SerialName("typeCode")
        var typeCode: String = "",
        // 分类名称
        @SerialName("typeName")
        var typeName: String = "",
    )

    @Serializable
    data class Status(
        // 返回中文描述
        @SerialName("cndesc")
        var cndesc: String = "",
        // 信息码
        @SerialName("infocode")
        var infocode: Int = 0
    )
}