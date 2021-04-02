package com.lothrazar.nologpunch;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigManager {

  private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
  private static ForgeConfigSpec COMMON_CONFIG;
  public static BooleanValue LOGS;
  public static BooleanValue DIRT;
  static {
    initConfig();
  }

  private static void initConfig() {
    CFG.comment("If whatever you are holding is not a tool or has no tool types, it will not be able to break these block tags").push(ModMain.MODID);
    LOGS = CFG.comment("Apply restrictions to minecaft:logs").define("logs", true);
    DIRT = CFG.comment("Apply restrictions to forge:dirt").define("dirt", false);
    CFG.pop(); // one pop for every push
    COMMON_CONFIG = CFG.build();
  }

  public static void setup() {
    final CommentedFileConfig configData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve(ModMain.MODID + ".toml"))
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    COMMON_CONFIG.setConfig(configData);
  }
}
