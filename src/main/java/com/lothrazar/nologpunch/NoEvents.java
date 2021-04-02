package com.lothrazar.nologpunch;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NoEvents {

  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    ItemStack stack = event.getPlayer().getHeldItemMainhand();
    if (!stack.getToolTypes().isEmpty()) {
      return; // is a tool so i don't care
    }
    BlockState state = event.getState();
    if (ConfigManager.LOGS.get() && state.isIn(BlockTags.LOGS)) {
      //if its a log, then cancel break if stack has no tool types (isEmpty implied)
      event.setCanceled(true);
      event.setResult(Result.DENY);
    }
    if (ConfigManager.DIRT.get() && state.isIn(Tags.Blocks.DIRT)) {
      event.setCanceled(true);
    }
    //    if (ConfigManager.STONE.get() && state.isIn(Tags.Blocks.STONE)) {
    //      event.setCanceled(true);
    //    }
    //
  }
}
