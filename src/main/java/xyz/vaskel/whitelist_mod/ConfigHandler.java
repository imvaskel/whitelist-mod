package xyz.vaskel.whitelist_mod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ConfigHandler {

    private final ForgeConfigSpec spec;

    private final IntValue time;

   public ConfigHandler(){
       final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

       builder.comment("Serverside Configuration.");
       builder.push("server");

       builder.comment("The time to wait, in seconds, to disable the config.");
       this.time = builder.defineInRange("time", 60, 1, 1000);

       builder.pop();

       this.spec = builder.build();
   }

   public int getTime(){
       return this.time.get();
   }

    public ForgeConfigSpec getSpec () {

        return this.spec;
    }

}
