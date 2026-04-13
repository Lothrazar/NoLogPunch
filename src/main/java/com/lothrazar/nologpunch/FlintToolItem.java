package com.lothrazar.nologpunch;

import com.google.common.collect.Sets;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ToolAction → ItemAbility, ToolActions → ItemAbilities
 */
public class FlintToolItem extends AxeItem {

//  private static final Set<ItemAbility> ACTIONS = Stream.concat(
//          ItemAbilities.DEFAULT_SHOVEL_ACTIONS.stream(),
//          ItemAbilities.DEFAULT_AXE_ACTIONS.stream())
//      .collect(Collectors.toCollection(Sets::newIdentityHashSet));
  public static final int MAX_DAMAGE = 256;

  public FlintToolItem(Properties builder) {
    super(Tiers.WOOD, builder.durability(MAX_DAMAGE));
  }

//  @Override
//  @OnlyIn(Dist.CLIENT)
//  public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
//    tooltip.add(Component.translatable(getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
//  }

//  @Override
//  public float getDestroySpeed(ItemStack stack, BlockState state) {
//    if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
//      return speed;
//    }
//    return super.getDestroySpeed(stack, state);
//  }

//  @Override
//  public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
//    return state.is(BlockTags.MINEABLE_WITH_SHOVEL)
//        || super.isCorrectToolForDrops(stack, state);
//  }
//
//  @Override
//  public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
//    // super (AxeItem) already handles DEFAULT_AXE_ACTIONS; only add shovel on top
//    var t=ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
//    return super.canPerformAction(stack, itemAbility)
//        || t;
//  }
}
