package xyz.vaskel.whitelist_mod;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.Logger;

public class WhitelistEventHandler {

    private int time;
    private Logger logger;

    public WhitelistEventHandler(int time){
        this.time = time * 1000;
        logger = WhitelistMod.getLogger();
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event){

        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

        // Ignore if the tick has just started, as we don't need to check the delta at the beginning of a tick.
        if (event.phase == TickEvent.Phase.START){
            return;
        }

        if (server.getCurrentTime() - WhitelistMod.getStartTime() >= time) {
            logger.info("Disabling Whitelist.");
            server.getPlayerList().setWhiteListEnabled(false);
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
