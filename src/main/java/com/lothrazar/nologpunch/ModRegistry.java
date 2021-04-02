package com.lothrazar.nologpunch;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {

  @SubscribeEvent
  public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
    IForgeRegistry<Item> r = event.getRegistry();
    //.group(MaterialRegistry.ITEM_GROUP)
    //flint_knife ?
    r.register(new FlintToolItem(new Item.Properties().maxDamage(64)).setRegistryName("flint_tool"));
    //only item: 1 flint tool make tool from either
    //stick flint
    //bamboo flint
    //sugar reeds flint
    //tool gives bark ? and maybe makes paths . dual shovel/axe !
    //bark gives bark axe and shovel -? damaged if possible
  }
}
