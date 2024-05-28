package com.catchingnow.icebox.sdk

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.annotation.IntDef

abstract class BaseSdk {
    @Suppress("SameParameterValue")
    @androidx.annotation.WorkerThread
    @androidx.annotation.RequiresPermission(IceBox.SDK_PERMISSION)
    protected abstract fun setAppEnabledSettings(
        context: Context,
        enable: Boolean,
        vararg packageNames: String,
    )

    @AppState
    protected abstract fun getAppEnabledSetting(info: ApplicationInfo): Int

    @IntDef(flag = true, value = [IceBox.FLAG_PM_HIDE, IceBox.FLAG_PM_DISABLE_USER])
    protected annotation class AppState
}
