# IceBox-SDK.kt (kotlin实现，仅保留冻结、解冻核心功能)

冰箱 SDK，可以在已安装并启用了冰箱的设备上，为第三方 App 提供冻结/解冻的功能。

冻结/解冻需要冰箱版本号 >= 3.6.0。

## 使用方法

### 依赖

添加依赖，在项目对应的文件中添加：

+ settings.gradle.kts
```kotlin
// 因为没有开源许可证，所以上传到了私有仓库
repositories {
    maven(url = "https://mirrors.lalaki.cn/IceBox-SDK.kt")
}
```
+ app/build.gradle.kts
```kotlin
dependencies {
    implementation("cn.lalaki:IceBox-SDK.kt:1.1")
}
```
也可以直接下载aar导入至项目：[IceBox-SDK.kt](https://github.com/lalakii/IceBox-SDK.kt/releases)

权限声明：
+ AndroidManifest.xml
```xml
<uses-permission android:name="com.catchingnow.icebox.SDK" />
```
## 代码示例，参考：[MainActivity.kt](sample/src/main/kotlin/cn/lalaki/demo10086/MainActivity.kt)

## 版权归原作者 [@heruoxin](https://github.com/heruoxin/IceBox-SDK) 所有，由 lalaki.cn 修改制作