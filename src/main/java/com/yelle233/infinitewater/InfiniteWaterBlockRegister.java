package com.yelle233.infinitewater;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class InfiniteWaterBlockRegister {


    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(InfiniteWater.MODID);


    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        InfiniteWaterItemRegister.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> blocks = BLOCKS.register(name,block);
        registerBlockItems(name, blocks);
        return blocks;
    }

    public static final DeferredBlock<Block> INFINITE_WATER_BLOCK = registerBlock("infinite_water_block",
            () -> new InfiniteWaterBlock(Block.Properties.of().strength(3.0F, 3.0F)));

}
