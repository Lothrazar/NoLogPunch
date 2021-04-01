package com.lothrazar.nologpunch;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModMain.MODID)
public class ModMain {

  public static final String MODID = "nologpunch";
  public static final Logger LOGGER = LogManager.getLogger();

  public ModMain() {
    ConfigManager.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
  }

  private void setup(final FMLCommonSetupEvent event) {
    //now all blocks/items exist  
    //    MinecraftForge.EVENT_BUS.register(new ItemEvents());
    MinecraftForge.EVENT_BUS.register(this);
    if (ConfigManager.TESTING.get()) {
      float test = Blocks.BEDROCK.getDefaultState().hardness;
      ModMain.LOGGER.info("accesstransformer.cfg test bedrock hardness = " + test);
    }
  }

  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    ItemStack stack = event.getPlayer().getHeldItemMainhand();
    if (event.getState().isIn(BlockTags.LOGS) && stack.getToolTypes().isEmpty()) {
      //if its a log, then cancel break if stack has no tool types (isEmpty implied)
      event.setCanceled(true);
      event.setResult(Result.DENY);
    }
    if (event.getState().isIn(Tags.Blocks.DIRT)) {
      //
    }
    if (event.getState().isIn(Tags.Blocks.STONE)) {
      //
    }
  }

  private void setupClient(final FMLClientSetupEvent event) {
    //for client side only setup
  }
}
