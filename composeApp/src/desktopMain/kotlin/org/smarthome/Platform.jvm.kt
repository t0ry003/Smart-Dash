package org.smarthome

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun isWeb(): Boolean = false

actual fun getPlatform(): Platform = JVMPlatform()