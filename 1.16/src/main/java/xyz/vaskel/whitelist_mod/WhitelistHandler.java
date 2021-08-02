package xyz.vaskel.whitelist_mod;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class WhitelistHandler {

    private static final int time = WhitelistMod.CONFIG.getTime() * 1000;
    private static int startTime;

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event){
        event.getServer().getPlayerList().setWhiteListEnabled(true);
        WhitelistMod.LOGGER.info("Whitelist has been enabled, waiting " + time / 1000 + "s to disable.");
        startTime = (int) event.getServer().getServerTime();
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event){

        // Return if we are at the start of a tick, as we don't need to check at the beginning and end of a tick.
        if (event.phase == TickEvent.Phase.START){
            return;
        }

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        if (server.getServerTime() - startTime >= time) {
            WhitelistMod.LOGGER.info("Disabled Whitelist after " + time / 1000 + "s.");
            server.getPlayerList().setWhiteListEnabled(false);
            MinecraftForge.EVENT_BUS.unregister(this);
        }

    }

}
