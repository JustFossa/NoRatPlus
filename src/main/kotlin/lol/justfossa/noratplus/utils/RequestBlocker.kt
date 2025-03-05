package lol.justfossa.noratplus.utils

import java.io.IOException
import java.net.*
import java.io.File
import kotlinx.serialization.json.*;


object RequestBlocker {
    private var isProxySet = false
    private var whitelist: List<String> = listOf();
    fun blockRequests() {
        if(isProxySet) return
        whitelist = ConfigHelper.getWhitelist().map { it.jsonPrimitive.content }

        println(whitelist)

        try {
            ProxySelector.setDefault(BlockedProxySelector(ProxySelector.getDefault(), whitelist))
            isProxySet = true
            println("Request blocking enabled")
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}

class BlockedProxySelector(private val defaultSelector: ProxySelector?, private val whitelist: List<String>): ProxySelector() {
    override fun select(uri: URI?): List<Proxy> {
        if(uri == null) return listOf(Proxy.NO_PROXY)

        if(whitelist.contains(uri.host)) {
            return defaultSelector?.select(uri) ?: listOf(Proxy.NO_PROXY)
        }
        println("Blocked request to ${uri.host}")

        return listOf()
    }

    override fun connectFailed(uri: URI?, sa: SocketAddress?, ioe: IOException?) {
        println("Connection failed: ${uri?.host}")
    }
}




