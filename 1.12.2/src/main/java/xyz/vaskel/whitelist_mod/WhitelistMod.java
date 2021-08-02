package xyz.vaskel.whitelist_mod;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = WhitelistMod.MODID, name = WhitelistMod.NAME, version = WhitelistMod.VERSION, acceptableRemoteVersions = "*", serverSideOnly = true)
public class WhitelistMod
{
    public static final String MODID = "whitelist_mod";
    public static final String NAME = "Whitelist Mod";
    public static final String VERSION = "1.0";
    public static final String FILENAME = "whitelist-mod";

    private static long startTime;
    private Configuration config;

    private int waitTime; // the wait time in seconds

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        config = new Configuration(new File(event.getModConfigurationDirectory().getPath(), FILENAME + ".cfg"));

        waitTime = config.get("general", "time", 60).getInt();

        MinecraftForge.EVENT_BUS.register(new WhitelistEventHandler(waitTime));

    }

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance(); // the event doesnt have the server??

        if (server.isDedicatedServer()) {

            startTime = System.currentTimeMillis();

            server.getPlayerList().setWhiteListEnabled(true);

            logger.info("Whitelist Enabled, waiting " + this.waitTime + "s to turn it off.");
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static long getStartTime() {
        return startTime;
    }

    public Configuration getConfig() {
        return config;
    }
}
