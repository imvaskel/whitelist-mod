package xyz.vaskel.whitelist_mod;

import net.minecraftforge.common.config.Config;

@Config(modid=WhitelistMod.MODID, name = WhitelistMod.FILENAME)
public class ConfigHandler {

    @Config.RangeInt(min=1)
    @Config.Name("time")
    @Config.Comment({
            "Sets the time in seconds to wait to disable the whitelist.",
            "The default is 60.",
    })
    public static int time = 60;

}
