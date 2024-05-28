package cn.lalaki.demo10086
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.catchingnow.icebox.sdk.IceBox
import kotlin.concurrent.thread

@Suppress("SetTextI18n")
class MainActivity : Activity() {
    private val requestCode = 0x233
    private var hasSdkPermission = false
    private val textView by lazy {
        TextView(this).apply {
            text =
                context.getString(R.string.icebox_sdk_kt) + "\r\n如果授权窗口无法弹出，尝试卸载重装此APP"
            textSize = 30f
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        if (requestCode == this.requestCode) {
            hasSdkPermission = grantResults.contains(PackageManager.PERMISSION_GRANTED)
            Log.d(packageName, "has icebox permission..")
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(textView)
        // 检查权限
        hasSdkPermission =
            checkSelfPermission(IceBox.SDK_PERMISSION) == PackageManager.PERMISSION_GRANTED
        if (!hasSdkPermission) {
            requestPermissions(
                arrayOf(IceBox.SDK_PERMISSION),
                requestCode,
            )
            Log.d(packageName, "request icebox permission.")
        } else {
            test()
        }
    }

    override fun onResume() {
        super.onResume()
        if (hasSdkPermission) {
            test()
            textView.text = getString(R.string.icebox_sdk_kt) + "\r\n已取得冰箱权限"
        }
    }

    @Suppress("InlinedApi")
    private fun test() {
        thread {
            val packageNames = arrayOf("org.example.app1", "org.example.app2", "org.example.appN")
            /**
             *  冻结或解冻应用，指定需要冻结App的包名，放在线程中执行
             *  修改 enable = true 冻结，enable = false 解冻
             */
            IceBox.setAppEnabledSettings(applicationContext, true, *packageNames)
        }
        /**
         * 获取应用状态，判断以何种方式冻结（pm disable-user or pm hide）,
         * 如果应用不存在，会引发异常，一般需要加上 try ... catch
         **/
        val state = IceBox.getAppEnabledSetting(applicationInfo)
        when (state) {
            IceBox.FLAG_PM_DISABLE_USER -> {
                Log.d(packageName, "disable-user.")
            }

            IceBox.FLAG_PM_HIDE -> {
                Log.d(packageName, "pm hide.")
            }

            else -> {
                Log.d(packageName, "normal.")
            }
        }
        // 冰箱App的包名
        val iceBoxPackageName = IceBox.PACKAGE_NAME
        Toast.makeText(this, iceBoxPackageName, Toast.LENGTH_SHORT).show()
    }
}
