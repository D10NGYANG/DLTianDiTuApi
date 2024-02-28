import com.d10ng.http.Http
import com.d10ng.tianditu.TianDiTuApiManager
import com.d10ng.tianditu.api.TianDiTuApi
import com.d10ng.tianditu.constant.TokenType
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Test {

    @Test
    fun test() {
        Http.init(true)

        TianDiTuApiManager.init("fb606872d339bbfe541c04775909d279", TokenType.ANDROID)

        runBlocking {
            val job = launch {
                Http.errorResponseFlow.collect {
                    println(it)
                }
            }
            val geocode = TianDiTuApi.getGeocode("佛山")
            println(geocode)
            val reGeocode = TianDiTuApi.getReGeocode(113.09728389219077, 23.103711052836463)
            println(reGeocode)
            val location = TianDiTuApi.getLocationSearchV2("广东北京路")
            println(location)
            job.cancel()
        }
    }
}