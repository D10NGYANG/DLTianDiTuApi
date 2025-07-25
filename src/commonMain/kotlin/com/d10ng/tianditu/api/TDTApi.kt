package com.d10ng.tianditu.api

import com.d10ng.tianditu.bean.Geocode
import com.d10ng.tianditu.bean.LocationSearch
import com.d10ng.tianditu.bean.PerimeterSearch
import com.d10ng.tianditu.bean.ReGeocode
import com.d10ng.tianditu.constant.TokenType
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object TDTApi {

    /** 访问域名 */
    internal const val BASE_URL: String  = "https://api.tianditu.gov.cn"

    /** 访问TOKEN */
    private var token: String = ""

    /** TOKEN类型 */
    private var tokenType = TokenType.WEB

    /** ktor客户端 */
    private var client = HttpClient()

    /**
     * 初始化
     * @param token String
     * @param type TokenType
     * @param client HttpClient
     */
    fun init(token: String, type: TokenType = TokenType.WEB, client: HttpClient) {
        this.token = token
        this.tokenType = type
        this.client = client
    }

    private suspend fun <T> apiCall(call: suspend HttpClient.() -> T): Result<T> = runCatching { client.call() }

    val useJson = Json {
        // 忽略JSON字符串里有但data class中没有的key
        ignoreUnknownKeys = true
        // 如果接收到的JSON字符串的value为null，但是data class中的对应属性不能为null，那就使用属性的默认值
        coerceInputValues = true
        // 如果创建data class实例时有些属性没有赋值，那就使用默认值进行转换成JSON字符串
        encodeDefaults = true
        // 属性放宽
        isLenient = true
    }

    /**
     * 地理编码
     * @param keyWord String 需要获取经纬度的详细地址
     * @return Result<Geocode>
     */
    suspend fun getGeocode(
        keyWord: String
    ): Result<Geocode> = apiCall {
        val text = get("${BASE_URL}/geocoder") {
            if (tokenType != TokenType.SERVER) {
                headers {
                    append("User-Agent", "Mozilla/5.0")
                }
            }
            parameter("ds", buildJsonObject {
                put("keyWord", keyWord)
            }.toString())
            parameter("tk", token)
        }.bodyAsText()
        useJson.decodeFromString(text)
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
    ): Result<ReGeocode> = apiCall {
        val text = get("${BASE_URL}/geocoder") {
            if (tokenType != TokenType.SERVER) {
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
            parameter("tk", token)
        }.bodyAsText()
        useJson.decodeFromString(text)
    }

    /**
     * 地名搜索V2.0
     * @param keyWord String 搜索的关键字。
     * @param mapBound String 查询的地图范围“minx,miny,maxx,maxy”，默认为全球范围-180,-90至180,90。
     * @param level Int 目前查询的级别，取值范围1-18，默认18。
     * @param specify String? 指定行政区的国标码（行政区划编码表）严格按照行政区划编码表中的（名称，gb码）。如指定的行政区划编码是省以上级别则返回是统计数据（不包括直辖市），9位国标码，如：北京：156110000或北京。
     * @param queryType Int 搜索类型，1:普通搜索（含地铁公交） 7：地名搜索
     * @param start Int 返回结果起始位（用于分页和缓存）默认0，取值范围0-300。
     * @param count Int 返回的结果数量（用于分页和缓存）默认100，取值范围1-300。
     * @param dataTypes String? 数据分类（分类编码表），参数可以分类名称或分类编码。多个分类用","隔开(英文逗号)。
     * @param show Int 返回poi结果信息类别，取值为1，则返回基本poi信息； 取值为2，则返回详细poi信息。
     * @return LocationSearch?
     */
    suspend fun getLocationSearchV2(
        keyWord: String,
        specify: String? = null,
        level: Int = 18,
        mapBound: String = "-180,-90,180,90",
        queryType: Int = 1,
        start: Int = 0,
        count: Int = 100,
        dataTypes: String? = null,
        show: Int = 1
    ): Result<LocationSearch> = apiCall {
        get("${BASE_URL}/v2/search") {
            if (tokenType != TokenType.SERVER) {
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
                if (dataTypes != null) put("dataTypes", dataTypes)
                put("show", show)
            }.toString())
            parameter("type", "query")
            parameter("tk", token)
        }.body()
    }

    /**
     * 周边搜索
     * @param keyWord String 搜索的关键字
     * @param lng Double 经度
     * @param lat Double 纬度
     * @param queryRadius Int 查询半径，单位：米，默认10000米，最大10000米。
     * @param start Int 返回结果起始位（用于分页和缓存）默认0，取值范围0-300。
     * @param count Int 返回的结果数量（用于分页和缓存）默认100，取值范围1-300。
     * @param dataTypes String? 数据分类（分类编码表），参数可以分类名称或分类编码。多个分类用","隔开(英文逗号)。
     * @param show Int 返回poi结果信息类别，取值为1，则返回基本poi信息； 取值为2，则返回详细poi信息。
     */
    suspend fun getPerimeterSearch(
        keyWord: String,
        lng: Double,
        lat: Double,
        queryRadius: Int = 10000,
        start: Int = 0,
        count: Int = 100,
        dataTypes: String? = null,
        show: Int = 1
    ): Result<PerimeterSearch> = apiCall {
        get("${BASE_URL}/v2/search"){
            if (tokenType != TokenType.SERVER) {
                headers {
                    append("User-Agent", "Mozilla/5.0")
                }
            }
            parameter("postStr", buildJsonObject {
                put("keyWord", keyWord)
                put("queryRadius", queryRadius)
                put("pointLonlat", "$lng,$lat")
                put("queryType", 3)
                put("start", start)
                put("count", count)
                if (dataTypes != null) put("dataTypes", dataTypes)
                put("show", show)
            }.toString())
            parameter("type", "query")
            parameter("tk", token)
        }.body()
    }
}