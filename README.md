# DLTianDiTuApi
天地图WEB API服务封装

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
  implementation 'com.github.D10NGYANG:DLTianDiTuApi:0.5.4'
  // 网络请求封装库
  implementation "com.github.D10NGYANG:DLHttpUtil:0.8.5"
  // 协程
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"
  // JSON序列化
  implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0"
}
```
3. 混淆
```properties
-keep class com.d10ng.tianditu.** {*;}
-dontwarn com.d10ng.tianditu.**
```
