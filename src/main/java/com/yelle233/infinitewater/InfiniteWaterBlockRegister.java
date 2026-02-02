package com.yelle233.infinitewater;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.infrastructure.config.CStress;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;


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
            () -> new InfiniteWaterBlock(Block.Properties.of().strength(3.0F, 3.0F).noOcclusion().requiresCorrectToolForDrops()));





}
