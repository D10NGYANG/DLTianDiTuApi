import com.d10ng.tianditu.api.TDTApi
import com.d10ng.tianditu.constant.TokenType
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Test {

    @Test
    fun test() {
        val token = System.getProperty("myTiandituToken")
            ?: throw IllegalStateException("myTiandituToken property not set")
        TDTApi.init(token, TokenType.ANDROID, HttpClient(CIO) {
            install(ContentNegotiation) {
                json(TDTApi.useJson)
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        //println(message)
                    }
                }
                level = LogLevel.ALL
            }
        })

        runBlocking {
            println(TDTApi.getGeocode("佛山"))
            println(TDTApi.getReGeocode(113.09728, 23.10371))
            println(TDTApi.getLocationSearchV2("丰田"))
            println(TDTApi.getPerimeterSearch("丰田", 113.09728, 23.10371))
        }
    }
}