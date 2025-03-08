package org.smarthome

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun isWeb(): Boolean = true

actual fun getPlatform(): Platform = WasmPlatform()