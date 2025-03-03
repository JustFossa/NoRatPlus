package lol.justfossa.noratplus.utils

import java.io.IOException
import java.net.URL
import java.net.URLConnection
import java.net.URLStreamHandler
import java.net.URLStreamHandlerFactory


class RequestUtils {
    companion object {
        fun blockRequests() {
            try {
                URL.setURLStreamHandlerFactory(object: URLStreamHandlerFactory {

                    override fun createURLStreamHandler(protocol: String): URLStreamHandler? {
                        if ("http".equals(protocol) || "https".equals(protocol)) {
                            return BlockedURLStreamHandler();
                        }
                        return null;
                    }
                } )
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class BlockedURLStreamHandler : URLStreamHandler() {

        override fun openConnection(url: URL): URLConnection  {
            throw IOException("Blocked: Cannot open connection to $url");
        }
    }


}