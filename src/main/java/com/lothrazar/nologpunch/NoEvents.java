package com.lothrazar.nologpunch;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NoEvents {

  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    ItemStack stack = event.getPlayer().getMainHandItem();
    if (!stack.getToolTypes().isEmpty() || event.getPlayer().isCreative()) {
      return; // is a tool (or player is creative) so i don't care
    }
    BlockState state = event.getState();
    if (ConfigManager.LOGS.get() && state.is(BlockTags.LOGS)) {
      //if its a log, then cancel break if stack has no tool types (isEmpty implied)
      event.setCanceled(true);
      event.setResult(Result.DENY);
    }
    if (ConfigManager.DIRT.get() && state.is(BlockTags.DIRT)) {
      event.setCanceled(true);
      event.setResult(Result.DENY);
    }
  }
}
