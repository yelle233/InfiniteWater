package com.yelle233.infinitewater.item;

import com.simibubi.create.api.stress.BlockStressValues;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class InfiniteWaterBlockItem extends BlockItem {

    public InfiniteWaterBlockItem(Block block, Properties props) {
        super(block, props);
    }

    @Override
    public void appendHoverText(ItemStack stack,
                                TooltipContext ctx,
                                List<Component> tooltip,
                                TooltipFlag flag) {
        super.appendHoverText(stack, ctx, tooltip, flag);

        double impact = BlockStressValues.getImpact(getBlock());

        if (impact > 0) {
            tooltip.add(Component.empty());
            tooltip.add(Component.translatable("create.tooltip.stressImpact")
                    .withStyle(ChatFormatting.GRAY));

            String impactStr = String.format("%.1f", impact);
            ChatFormatting levelColor = getImpactColor(impact);
            String levelBar = getImpactBar(impact);

            MutableComponent line = Component.literal(" " + impactStr + "x ")
                    .withStyle(ChatFormatting.AQUA)
                    .append(Component.translatable("create.generic.unit.rpm")
                            .withStyle(ChatFormatting.DARK_GRAY));

            tooltip.add(line);

            tooltip.add(Component.literal(" " + levelBar)
                    .withStyle(levelColor));
        }
    }


    private ChatFormatting getImpactColor(double impact) {
        if (impact <= 4) return ChatFormatting.GREEN;
        if (impact <= 8) return ChatFormatting.YELLOW;
        if (impact <= 16) return ChatFormatting.GOLD;
        return ChatFormatting.RED;
    }


    private String getImpactBar(double impact) {
        int level;
        String translationKey;

        if (impact <= 4) {
            level = 1;
            translationKey = "infinitewater.stress.level.low";
        } else if (impact <= 8) {
            level = 2;
            translationKey = "infinitewater.stress.level.medium";
        } else if (impact <= 16) {
            level = 3;
            translationKey = "infinitewater.stress.level.high";
        } else {
            level = 4;
            translationKey = "infinitewater.stress.level.extreme";
        }


        StringBuilder bar = new StringBuilder(" ");
        for (int i = 0; i < 4; i++) {
            bar.append(i < level ? "█" : "░");
        }
        bar.append(" ");

        bar.append(Component.translatable(translationKey).getString());

        return bar.toString();
    }
}
