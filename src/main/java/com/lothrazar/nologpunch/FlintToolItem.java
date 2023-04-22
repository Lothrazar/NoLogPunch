package com.lothrazar.nologpunch;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.common.collect.Sets;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class FlintToolItem extends AxeItem {

  private static final Set<ToolAction> ACTIONS = Stream.of(ToolActions.SHOVEL_DIG)
      .collect(Collectors.toCollection(Sets::newIdentityHashSet));

  public FlintToolItem(Properties builder) {
    super(Tiers.WOOD, 6.5F, -3.3F, builder.durability(256));
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    tooltip.add(Component.translatable(getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
  }

  @Override
  public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
    return super.canPerformAction(stack, toolAction) || ACTIONS.contains(toolAction);
  }
}
