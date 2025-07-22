# DLTianDiTuApi

![Version](https://img.shields.io/badge/version-1.2.0-brightgreen)
![Kotlin](https://img.shields.io/badge/kotlin-2.2.0-blue)
![Platform](https://img.shields.io/badge/platform-jvm%20%7C%20js%20%7C%20ios%20%7C%20macos%20%7C%20linux-lightgrey)

天地图 WEB API 服务封装

## 简介
这是一个用于访问天地图Web API服务的Kotlin多平台SDK，支持JVM、JS、iOS、macOS和Linux平台。

## 功能
- 🌍 地理编码（地址转坐标）
- 📍 逆地理编码（坐标转地址）
- 🔍 地名搜索
- 🏙️ 周边搜索

## 使用说明
### 1. 添加仓库
```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        // ...
        maven("https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository")
    }
}
```

### 2. 添加依赖
```kotlin
// build.gradle.kts
dependencies {
    // 天地图API
    implementation("com.github.D10NGYANG:DLTianDiTuApi:1.2.0")
    // 协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    // JSON序列化
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    // ktor客户端
    implementation("io.ktor:ktor-client-core:3.2.2")
    // ktor引擎，可选其他的
    implementation("io.ktor:ktor-client-cio:3.2.2")
    // 必须，内容序列化
    implementation("io.ktor:ktor-client-content-negotiation:3.2.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.2")
}
```

### 3. 混淆
```properties
-keep class com.d10ng.tianditu.** {*;}
-dontwarn com.d10ng.tianditu.**
```

## 使用示例

### 初始化
```kotlin
import com.d10ng.tianditu.api.TDTApi
import com.d10ng.tianditu.constant.TokenType
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

// 创建HttpClient
val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(TDTApi.useJson)
    }
}

// 初始化API（需要在天地图官网申请TOKEN）
TDTApi.init(
    token = "你的天地图TOKEN", 
    type = TokenType.WEB,  // 或 TokenType.SERVER
    client = client
)
```

### 地理编码（地址转坐标）
```kotlin
import com.d10ng.tianditu.api.TDTApi

suspend fun geocodeExample() {
    val result = TDTApi.getGeocode("北京市朝阳区建国路88号")
    result.onSuccess { geocode ->
        println("经度: ${geocode.location?.lon}, 纬度: ${geocode.location?.lat}")
    }.onFailure {
        println("获取失败: ${it.message}")
    }
}
```

### 逆地理编码（坐标转地址）
```kotlin
import com.d10ng.tianditu.api.TDTApi

suspend fun reverseGeocodeExample() {
    val result = TDTApi.getReGeocode(116.46, 39.92)
    result.onSuccess { reGeocode ->
        println("地址: ${reGeocode.addressComponents?.province}${reGeocode.addressComponents?.city}${reGeocode.addressComponents?.district}${reGeocode.addressComponents?.township}${reGeocode.addressComponents?.street}${reGeocode.addressComponents?.streetNumber}")
    }.onFailure {
        println("获取失败: ${it.message}")
    }
}
```

### 地名搜索
```kotlin
import com.d10ng.tianditu.api.TDTApi

suspend fun locationSearchExample() {
    val result = TDTApi.getLocationSearchV2(
        keyWord = "餐厅",
        specify = "北京市", // 可选，指定搜索区域
        count = 20 // 返回结果数量
    )
    result.onSuccess { search ->
        search.pois?.forEach { poi ->
            println("名称: ${poi.name}, 地址: ${poi.address}")
            println("经度: ${poi.lonlat?.split(",")?.get(0)}, 纬度: ${poi.lonlat?.split(",")?.get(1)}")
        }
    }.onFailure {
        println("搜索失败: ${it.message}")
    }
}
```

### 周边搜索
```kotlin
import com.d10ng.tianditu.api.TDTApi

suspend fun perimeterSearchExample() {
    val result = TDTApi.getPerimeterSearch(
        keyWord = "超市",
        lng = 116.46,
        lat = 39.92,
        queryRadius = 5000, // 搜索半径，单位：米
        count = 20 // 返回结果数量
    )
    result.onSuccess { search ->
        search.pois?.forEach { poi ->
            println("名称: ${poi.name}, 地址: ${poi.address}")
            println("距离: ${poi.distance}米")
        }
    }.onFailure {
        println("搜索失败: ${it.message}")
    }
}
```