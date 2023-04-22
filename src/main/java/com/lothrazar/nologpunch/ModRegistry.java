package com.lothrazar.nologpunch;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MODID);

  @SubscribeEvent
  public static void addCreative(CreativeModeTabEvent.BuildContents event) {
    if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
      event.accept(FLINT_TOOL.get());
    }
  }

  public static final RegistryObject<Item> FLINT_TOOL = ITEMS.register("flint_tool", () -> new FlintToolItem(new Item.Properties()));
}
