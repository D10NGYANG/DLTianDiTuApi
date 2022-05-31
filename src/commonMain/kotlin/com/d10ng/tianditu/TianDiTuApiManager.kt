package com.d10ng.tianditu

import com.d10ng.tianditu.constant.TokenType

object TianDiTuApiManager {

    /** 访问域名 */
    const val baseUrl: String  = "https://api.tianditu.gov.cn"

    /** 访问TOKEN */
    private var token: String = ""

    /** TOKEN类型 */
    private var tokenType = TokenType.WEB

    /**
     * 初始化
     * @param token String
     * @param type TokenType
     */
    fun init(token: String, type: TokenType = TokenType.WEB) {
        this.token = token
        this.tokenType = type
    }

    fun getToken() = token
    fun getTokenType() = tokenType
}