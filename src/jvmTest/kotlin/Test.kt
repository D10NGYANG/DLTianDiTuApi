import com.d10ng.http.Http
import com.d10ng.tianditu.TianDiTuApiManager
import com.d10ng.tianditu.api.TianDiTuApi
import com.d10ng.tianditu.constant.TokenType
import kotlinx.coroutines.runBlocking
import org.junit.Test

class Test {

    @Test
    fun test() {
        Http.init(TianDiTuApiManager.baseUrl, true)
        TianDiTuApiManager.init("fb606872d339bbfe541c04775909d279", TokenType.WEB)
        runBlocking {
            TianDiTuApi.getGeocode("佛山")
            TianDiTuApi.getReGeocode(113.116428, 23.024395)
            TianDiTuApi.getLocationSearchV2("广东北京路")
        }
    }
}