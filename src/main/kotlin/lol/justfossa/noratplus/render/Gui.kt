package lol.justfossa.noratplus.render

import net.minecraft.client.gui.GuiMainMenu
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class Gui {
     var blockedRequests = 0;


    companion object {
        val gui = Gui()
        public fun incrementBlockedRequests() {
             gui.blockedRequests++;
        }
    }


    @SubscribeEvent
    fun onGuiRender(event: GuiScreenEvent.DrawScreenEvent.Post) {
        if(event.gui is GuiMainMenu) {
            val mc = (event.gui as GuiMainMenu).mc
            val sr = ScaledResolution(mc)
            val x = sr.scaledWidth / 2
            val y = sr.scaledHeight / 4

            mc.fontRendererObj.drawString("Blocked ${blockedRequests} requests", x - mc.fontRendererObj.getStringWidth("Blocked ${blockedRequests} requests") / 2 , y, 0xFF0000)
        }
    }
}