package lol.justfossa.noratplus

import lol.justfossa.noratplus.utils.RequestUtils
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = "noratplus", useMetadata = true)
class NoRatPlus {
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        RequestUtils.blockRequests()
    }
}
