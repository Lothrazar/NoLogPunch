package com.lothrazar.nologpunch;

import com.google.common.collect.Sets;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
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
  // WOOD's stats, but with a much higher custom durability
  private static final ToolMaterial FLINT_MATERIAL = new ToolMaterial(
      ToolMaterial.WOOD.incorrectBlocksForDrops(), MAX_DAMAGE, ToolMaterial.WOOD.speed(),
      ToolMaterial.WOOD.attackDamageBonus(), ToolMaterial.WOOD.enchantmentValue(), ToolMaterial.WOOD.repairItems());

  public FlintToolItem(Properties builder) {
    super(FLINT_MATERIAL, 6.0F, -3.2F, builder);
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
