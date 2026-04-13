package com.lothrazar.nologpunch;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.common.collect.Sets;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class FlintToolItem extends AxeItem {

  // RIP ToolActions apparently? ToolAction → ItemAbility, ToolActions → ItemAbilities
  private static final Set<ItemAbility> ACTIONS = Stream.of(ItemAbilities.SHOVEL_DIG)
      .collect(Collectors.toCollection(Sets::newIdentityHashSet));

  public FlintToolItem(Properties builder) {
    super(Tiers.WOOD, builder.durability(256));
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
    tooltip.add(Component.translatable(getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
  }

  @Override
  public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
    return super.canPerformAction(stack, itemAbility) || ACTIONS.contains(itemAbility);
  }
}
