import com.d10ng.http.Http
import com.d10ng.tianditu.TianDiTuApiManager
import com.d10ng.tianditu.api.TianDiTuApi
import com.d10ng.tianditu.bean.Geocode
import com.d10ng.tianditu.constant.TokenType
import io.ktor.util.reflect.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Test {

    @Test
    fun test() {
        Http.init(TianDiTuApiManager.baseUrl, true)

        TianDiTuApiManager.init("5bb740ffd3a80fb3963e022454eca6e2", TokenType.WEB)

        println(typeInfo<Geocode>())

        runBlocking {
            launch {
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
        }
    }
}