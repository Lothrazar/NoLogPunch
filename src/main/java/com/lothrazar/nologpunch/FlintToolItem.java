package com.lothrazar.nologpunch;

import com.google.common.collect.Sets;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class FlintToolItem extends ToolItem {

  public FlintToolItem(Properties builder) {
    super(4F, -2.8F, ItemTier.WOOD, Sets.newHashSet(),
        builder.addToolType(ToolType.AXE, 0).addToolType(ToolType.SHOVEL, 0).group(ItemGroup.TOOLS));
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    TranslationTextComponent t = new TranslationTextComponent(getTranslationKey() + ".tooltip");
    t.mergeStyle(TextFormatting.GRAY);
    tooltip.add(t);
  }

  /**
   * Called when this item is used when targetting a Block
   */
  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    World world = context.getWorld();
    BlockPos blockpos = context.getPos();
    BlockState blockstate = world.getBlockState(blockpos);
    PlayerEntity playerentity = context.getPlayer();
    BlockState block = blockstate.getToolModifiedState(world, blockpos, playerentity, context.getItem(), net.minecraftforge.common.ToolType.AXE);
    if (block != null) {
      //axe action
      world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
      if (!world.isRemote) {
        world.setBlockState(blockpos, block, 11);
        if (playerentity != null) {
          context.getItem().damageItem(1, playerentity, (p) -> {
            p.sendBreakAnimation(context.getHand());
          });
        }
        //chance to drop stick?
      }
      return ActionResultType.func_233537_a_(world.isRemote);
    }
    else {
      //try shovel action
      BlockState blockstate1 = blockstate.getToolModifiedState(world, blockpos, playerentity, context.getItem(), net.minecraftforge.common.ToolType.SHOVEL);
      BlockState blockstate2 = null;
      if (blockstate1 != null && world.isAirBlock(blockpos.up())) {
        world.playSound(context.getPlayer(), blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
        blockstate2 = blockstate1;
      }
      else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT)) {
        if (!world.isRemote()) {
          world.playEvent((PlayerEntity) null, 1009, blockpos, 0);
        }
        CampfireBlock.extinguish(world, blockpos, blockstate);
        blockstate2 = blockstate.with(CampfireBlock.LIT, Boolean.valueOf(false));
      }
      if (blockstate2 != null) {
        if (!world.isRemote) {
          world.setBlockState(blockpos, blockstate2, 11);
          if (playerentity != null) {
            context.getItem().damageItem(1, playerentity, (player) -> {
              player.sendBreakAnimation(context.getHand());
            });
          }
        }
        return ActionResultType.func_233537_a_(world.isRemote);
      }
      else {
        //neither
        return ActionResultType.PASS;
      }
    }
  }
}
