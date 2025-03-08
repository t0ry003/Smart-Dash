package org.smarthome

import kotlin.system.exitProcess

actual fun exitApp(context: Any?) {
    exitProcess(0)  // Close the Desktop app
}

actual fun getExitContext(): Any? = null
