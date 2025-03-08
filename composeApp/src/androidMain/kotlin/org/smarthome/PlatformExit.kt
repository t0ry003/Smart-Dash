package org.smarthome

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

actual fun exitApp(context: Any?) {
    (context as? Activity)?.finish()  // Close the Android app
}

actual fun getExitContext(): Any? {
    return null
}