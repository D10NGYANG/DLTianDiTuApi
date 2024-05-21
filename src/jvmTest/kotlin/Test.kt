import com.d10ng.common.log.LogIt
import com.d10ng.http.Api
import com.d10ng.http.Http
import com.d10ng.http.setDefaultHttpResponseValidator
import com.d10ng.tianditu.TianDiTuApiManager
import com.d10ng.tianditu.api.TianDiTuApi
import com.d10ng.tianditu.constant.TokenType
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Test {

    @Test
    fun test() {

        TianDiTuApiManager.init("fb606872d339bbfe541c04775909d279", TokenType.ANDROID)

        runBlocking {
            LogTest.debug = true
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(json = com.d10ng.common.transform.json)
                }
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            LogTest.i(message)
                        }
                    }
                    level = LogLevel.ALL
                }
                setDefaultHttpResponseValidator()
            }
            Api.client = client
            val job = launch {
                Http.errorResponseMessageFlow.collect {
                    println("错误信息：$it")
                }
            }
            println(TianDiTuApi.getGeocode("佛山"))
            println(TianDiTuApi.getReGeocode(113.09728, 23.10371))
            println(TianDiTuApi.getLocationSearchV2("丰田"))
            println(TianDiTuApi.getPerimeterSearch("丰田",113.09728, 23.10371))
            job.cancel()
        }
    }
}

object LogTest : LogIt("test")