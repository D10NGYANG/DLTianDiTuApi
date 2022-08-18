# DLTianDiTuApi
天地图WEB API服务封装

[![](https://jitpack.io/v/D10NGYANG/DLTianDiTuApi.svg)](https://jitpack.io/#D10NGYANG/DLTianDiTuApi)

# 使用说明
1. Add the JitPack repository to your build file
```build.gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the dependency
```build.gradle
dependencies {
  // 天地图API
  implementation 'com.github.D10NGYANG:DLTianDiTuApi:$release'
  // 网络请求封装库
  implementation "com.github.D10NGYANG:DLHttpUtil:0.6"
  // 协程
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
  // JSON序列化
  implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
}
```
