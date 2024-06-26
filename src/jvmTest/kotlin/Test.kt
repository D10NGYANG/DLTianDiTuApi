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

        TianDiTuApiManager.init("fb606872d339bbfe541c04775909d279", TokenType.ANDROID, true)

        runBlocking {
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