package com.yelle233.infinitewater;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class InfiniteWaterTabRegister {

    public static final DeferredRegister<CreativeModeTab> TABS=DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InfiniteWater.MODID);

    public static final Supplier<CreativeModeTab> INFINITE_WATER_TAB =
            TABS.register("infinite_water_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.get()))
                    .title(Component.translatable("itemGroup.infinitewater"))
                    .displayItems((parameters, output) -> {
                        output.accept(InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.get());
                    }).build());
}
