import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.serialization.json.*;

object ConfigHelper {
    fun getWhitelist(): JsonArray {
        val whitelistFile = File(getMinecraftConfigPath()," whitelist.json")

        if(whitelistFile.exists()) {
            return Json.decodeFromString(whitelistFile.readText().trimIndent())
        }

        return JsonArray(emptyList())
    }
    private fun getMinecraftConfigPath(): File {
        val userHome = System.getProperty("user.home")

        // Possible Minecraft folders depending on the launcher
        val possibleDirs = listOf(
            File(userHome, "AppData/Roaming/.minecraft/config"),  // Windows Mojang default
            File(userHome, "PrismLauncher/instances/Default/config"),  // PrismLauncher (may vary)
            File(userHome, "MultiMC/instances/Default/config"),  // MultiMC (may vary)
            File(userHome, ".local/share/prismlauncher/instances/Default/config"),  // Linux PrismLauncher
            File(userHome, ".local/share/multimc/instances/Default/config")  // Linux MultiMC
        )

        // Find the first existing Minecraft config folder
        return possibleDirs.firstOrNull { it.exists() } ?: possibleDirs.first()
    }

    fun downloadConfigFile() {
        val url = URL("https://raw.githubusercontent.com/JustFossa/NoRatPlus/refs/heads/main/src/main/resources/whitelist.json") // GitHub Raw URL
        val mcConfigPath = File(getMinecraftConfigPath(), "whitelist.json") // Save inside detected config folder

        try {
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                mcConfigPath.parentFile?.mkdirs() // Create config directory if missing

                FileOutputStream(mcConfigPath).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }

                println("✅ Downloaded whitelist.json to: ${mcConfigPath.absolutePath}")
            } else {
                println("❌ Failed to download whitelist.json. Response code: ${connection.responseCode}")
            }
        } catch (e: Exception) {
            println("❌ Error downloading whitelist.json: ${e.message}")
        }
    }
}

