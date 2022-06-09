package com.d10ng.tianditu.api

import com.d10ng.http.Api
import com.d10ng.http.Http
import com.d10ng.tianditu.TianDiTuApiManager
import com.d10ng.tianditu.bean.Geocode
import com.d10ng.tianditu.bean.LocationSearch
import com.d10ng.tianditu.bean.ReGeocode
import com.d10ng.tianditu.constant.TokenType
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object TianDiTuApi {

    /**
     * 地理编码
     * @param keyWord String 需要获取经纬度的详细地址
     * @return Geocode?
     */
    suspend fun getGeocode(
        keyWord: String
    ): Geocode? = Api.handler {
        val text = it.get("${TianDiTuApiManager.baseUrl}/geocoder") {
            if (TianDiTuApiManager.getTokenType() != TokenType.SERVER) {
                headers {
                    append("User-Agent", "Mozilla/5.0")
                }
            }
            parameter("ds", buildJsonObject {
                put("keyWord", keyWord)
            }.toString())
            parameter("tk", TianDiTuApiManager.getToken())
        }.bodyAsText()
        Http.json.decodeFromString(text)
    }

    /**
     * 逆地理编码
     * @param lng Double 经度
     * @param lat Double 纬度
     * @return ReGeocode?
     */
    suspend fun getReGeocode(
        lng: Double,
        lat: Double
    ): ReGeocode? = Api.handler {
        val text = it.get("${TianDiTuApiManager.baseUrl}/geocoder") {
            if (TianDiTuApiManager.getTokenType() != TokenType.SERVER) {
                headers {
                    append("User-Agent", "Mozilla/5.0")
                }
            }
            parameter("postStr", buildJsonObject {
                put("lon", lng)
                put("lat", lat)
                put("ver", 1)
            }.toString())
            parameter("type", "geocode")
            parameter("tk", TianDiTuApiManager.getToken())
        }.bodyAsText()
        Http.json.decodeFromString(text)
    }

    /**
     * 地名搜索V2.0
     * @param keyWord String
     * @param specify String?
     * @param start Int
     * @param count Int
     * @param show Int
     * @return LocationSearch?
     */
    suspend fun getLocationSearchV2(
        keyWord: String,
        specify: String? = null,
        level: Int = 18,
        mapBound: String = "-180,-90,180,90",
        queryType: Int = 1,
        start: Int = 0,
        count: Int = 30,
        show: Int = 1
    ): LocationSearch? = Api.handler {
        it.get("${TianDiTuApiManager.baseUrl}/v2/search") {
            if (TianDiTuApiManager.getTokenType() != TokenType.SERVER) {
                headers {
                    append("User-Agent", "Mozilla/5.0")
                }
            }
            parameter("postStr", buildJsonObject {
                put("keyWord", keyWord)
                if (specify != null) put("specify", specify)
                put("level", level)
                put("mapBound", mapBound)
                put("queryType", queryType)
                put("start", start)
                put("count", count)
                put("show", show)
            }.toString())
            parameter("type", "query")
            parameter("tk", TianDiTuApiManager.getToken())
        }.body()
    }
}