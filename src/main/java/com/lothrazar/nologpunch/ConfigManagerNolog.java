package com.lothrazar.nologpunch;

import com.lothrazar.library.config.ConfigTemplate;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ConfigManagerNolog extends ConfigTemplate {

  private static ForgeConfigSpec CONFIG;
  public static BooleanValue LOGS;
  static {
    final ForgeConfigSpec.Builder BUILDER = builder();
    BUILDER.comment("If whatever you are holding is not a tool or has no tool types, it will not be able to break these block tags").push(ModMain.MODID);
    LOGS = BUILDER.comment("Apply restrictions to minecaft:logs").define("logs", true);
    BUILDER.pop(); // one pop for every push
    CONFIG = BUILDER.build();
  }

  public ConfigManagerNolog() {
    CONFIG.setConfig(setup(ModMain.MODID));
  }
}
