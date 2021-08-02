package xyz.vaskel.whitelist_mod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WhitelistMod.MODID)
public class WhitelistMod {

    public static final String MODID = "whitelist_mod";
    public static final ConfigHandler CONFIG = new ConfigHandler();

    public static final Logger LOGGER = LogManager.getLogger();

    public WhitelistMod() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CONFIG.getSpec());
        MinecraftForge.EVENT_BUS.register(new WhitelistHandler());
    }
}
