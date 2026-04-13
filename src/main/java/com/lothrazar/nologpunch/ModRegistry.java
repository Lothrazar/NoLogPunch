package com.lothrazar.nologpunch;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid=ModMain.MODID)
public class ModRegistry {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, ModMain.MODID);

  @SubscribeEvent
  public static void buildContents(BuildCreativeModeTabContentsEvent event) {
    // Add to ingredients tab
    if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
      event.accept(FLINT_TOOL.get());
    }
  }

  public static final DeferredHolder<Item,FlintToolItem> FLINT_TOOL = ITEMS.register("flint_tool", () -> new FlintToolItem(new Item.Properties()));
}
