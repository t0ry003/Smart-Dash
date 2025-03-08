package org.smarthome

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun isWeb(): Boolean = false

actual fun getPlatform(): Platform = AndroidPlatform()