# DLTianDiTuApi
天地图WEB API服务封装

# 使用说明
1. Add the JitPack repository to your build file
```build.gradle
allprojects {
  repositories {
    ...
    maven { url 'https://raw.githubusercontent.com/D10NGYANG/maven-repo/main/repository'}
  }
}
```
2. Add the dependency
```build.gradle
dependencies {
  // 天地图API
  implementation 'com.github.D10NGYANG:DLTianDiTuApi:0.5.1'
  // 网络请求封装库
  implementation "com.github.D10NGYANG:DLHttpUtil:0.8.2"
  // 协程
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1"
  // JSON序列化
  implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1"
}
```
3. 混淆
```properties
-keep class com.d10ng.tianditu.** {*;}
-dontwarn com.d10ng.tianditu.**
```
