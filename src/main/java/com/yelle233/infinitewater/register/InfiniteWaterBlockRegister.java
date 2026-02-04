package com.yelle233.infinitewater.register;

import com.yelle233.infinitewater.InfiniteWater;
import com.yelle233.infinitewater.block.InfiniteWaterBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;


public class InfiniteWaterBlockRegister {



    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(InfiniteWater.MODID);


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> blocks = BLOCKS.register(name,block);
        return blocks;
    }

    public static final DeferredBlock<Block> INFINITE_WATER_BLOCK = registerBlock("infinite_water_block",
            () -> new InfiniteWaterBlock(Block.Properties.of().strength(3.0F, 3.0F).noOcclusion().requiresCorrectToolForDrops()));




}
