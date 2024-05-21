package com.d10ng.tianditu

import com.d10ng.http.setDefaultHttpResponseValidator
import com.d10ng.tianditu.constant.TokenType
import com.d10ng.tianditu.utils.Log
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

object TianDiTuApiManager {

    /** 访问域名 */
    internal const val BASE_URL: String  = "https://api.tianditu.gov.cn"

    /** 访问TOKEN */
    private var token: String = ""

    /** TOKEN类型 */
    private var tokenType = TokenType.WEB

    // 网络请求客户端
    internal val client by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(com.d10ng.common.transform.json)
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i(message)
                    }
                }
                level = LogLevel.ALL
            }
            setDefaultHttpResponseValidator()
        }
    }

    /**
     * 初始化
     * @param token String
     * @param type TokenType
     */
    fun init(token: String, type: TokenType = TokenType.WEB, debug: Boolean = false) {
        this.token = token
        this.tokenType = type
        Log.debug = debug
    }

    fun getToken() = token
    fun getTokenType() = tokenType
}