package com.lothrazar.nologpunch;

import java.util.List;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolActions;

public class FlintToolItem extends DiggerItem {

  public FlintToolItem(Properties builder) {
    super(4F, -2.8F, Tiers.WOOD, BlockTags.MINEABLE_WITH_AXE, builder.tab(CreativeModeTab.TAB_TOOLS));
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    TranslatableComponent t = new TranslatableComponent(getDescriptionId() + ".tooltip");
    t.withStyle(ChatFormatting.GRAY);
    tooltip.add(t);
  }

  /**
   * Called when this item is used when targetting a Block
   */
  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level world = context.getLevel();
    BlockPos blockpos = context.getClickedPos();
    BlockState blockstate = world.getBlockState(blockpos);
    Player playerentity = context.getPlayer();
    BlockState block = blockstate.getToolModifiedState(world, blockpos, playerentity, context.getItemInHand(), ToolActions.AXE_DIG);
    if (block != null) {
      //axe action
      world.playSound(playerentity, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
      if (!world.isClientSide) {
        world.setBlock(blockpos, block, 11);
        if (playerentity != null) {
          context.getItemInHand().hurtAndBreak(1, playerentity, (p) -> {
            p.broadcastBreakEvent(context.getHand());
          });
        }
        //chance to drop stick?
      }
      return InteractionResult.sidedSuccess(world.isClientSide);
    }
    else {
      //try shovel action
      BlockState blockstate1 = blockstate.getToolModifiedState(world, blockpos, playerentity, context.getItemInHand(), ToolActions.SHOVEL_DIG);
      BlockState blockstate2 = null;
      if (blockstate1 != null && world.isEmptyBlock(blockpos.above())) {
        world.playSound(context.getPlayer(), blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
        blockstate2 = blockstate1;
      }
      else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
        if (!world.isClientSide()) {
          world.levelEvent(playerentity, 1009, blockpos, 0);
        }
        CampfireBlock.dowse(playerentity, world, blockpos, blockstate);
        blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
      }
      if (blockstate2 != null) {
        if (!world.isClientSide) {
          world.setBlock(blockpos, blockstate2, 11);
          if (playerentity != null) {
            context.getItemInHand().hurtAndBreak(1, playerentity, (player) -> {
              player.broadcastBreakEvent(context.getHand());
            });
          }
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
      }
      else {
        //neither
        return InteractionResult.PASS;
      }
    }
  }
}
