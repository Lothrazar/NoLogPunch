package com.lothrazar.nologpunch;

import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {

  @SubscribeEvent
  public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
    IForgeRegistry<Item> r = event.getRegistry(); 
    r.register(new FlintToolItem(new Item.Properties().durability(64)).setRegistryName("flint_tool"));
 
  }
}
