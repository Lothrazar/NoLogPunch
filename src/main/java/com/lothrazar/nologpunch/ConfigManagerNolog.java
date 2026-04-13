package com.lothrazar.nologpunch;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigManagerNolog  {

  static ModConfigSpec CONFIG;
  public static ModConfigSpec.BooleanValue LOGS;
  static {
    final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    BUILDER.comment("If whatever you are holding is not a tool or has no tool types, it will not be able to break these block tags").push(ModMain.MODID);
    LOGS = BUILDER.comment("Apply restrictions to minecaft:logs").define("logs", true);
    BUILDER.pop(); // one pop for every push
    CONFIG = BUILDER.build();
  }

//  public ConfigManagerNolog() {
//    CONFIG.setConfig(setup(ModMain.MODID));
//  }
}
