package com.lothrazar.nologpunch;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MODID);
  public static final RegistryObject<Item> FLINT_TOOL = ITEMS.register("flint_tool", () -> new FlintToolItem(new Item.Properties()));
}
