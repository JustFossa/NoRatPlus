package lol.justfossa.noratplus

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = "noratplus", useMetadata = true)
class NoRatPlus {
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        println("NoRatPlus PreInit")
    }
}
