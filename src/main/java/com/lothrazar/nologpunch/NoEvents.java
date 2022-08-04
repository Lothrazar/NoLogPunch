package com.lothrazar.nologpunch;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NoEvents {

  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    Player player = event.getPlayer();
    ItemStack stack = player.getMainHandItem();
    BlockState state = event.getState();
    if (stack.isCorrectToolForDrops(state) || player.isCreative()) {
      return; // is a tool (or player is creative) so i don't care
    }
    if (ConfigManager.LOGS.get() && state.is(BlockTags.LOGS)) {
      //if its a log, then cancel break if stack has no tool types (isEmpty implied)
      event.setCanceled(true);
      event.setResult(Result.DENY);
    }
  }
}
