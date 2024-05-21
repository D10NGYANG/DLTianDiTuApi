# DLTianDiTuApi
天地图 WEB API 服务封装

# 使用说明
1. 添加仓库
```build.gradle
allprojects {
  repositories {
    ...
    maven { url 'https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository'}
  }
}
```
2. 添加依赖
```build.gradle
dependencies {
    // 天地图API
    implementation 'com.github.D10NGYANG:DLTianDiTuApi:1.0.0'
    // 协程
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1'
    // JSON序列化
    implementation 'org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3'
    // ktor核心库
    implementation 'io.ktor:ktor-client-core:2.3.11'
    // 网络请求封装库
    implementation 'com.github.D10NGYANG:DLHttpUtil:1.0.0'
    // 通用工具
    implementation 'com.github.D10NGYANG:DLCommonUtil:0.1.2'
}
```
3. 混淆
```properties
-keep class com.d10ng.tianditu.** {*;}
-dontwarn com.d10ng.tianditu.**
```
