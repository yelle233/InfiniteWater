package com.yelle233.infinitewater;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class InfiniteWaterItemRegister {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(InfiniteWater.MODID);


    public static final DeferredItem<Item> INFINITE_WATER_BLOCK =
            ITEMS.register("infinite_water_block", () -> new InfiniteWaterBlockItem(InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> WATER_STORAGE_CORE=
            ITEMS.register("water_storage_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_WATER_STORAGE_CORE =
            ITEMS.register("incomplete_water_storage_core",
                    () -> new Item(new Item.Properties()));
}
