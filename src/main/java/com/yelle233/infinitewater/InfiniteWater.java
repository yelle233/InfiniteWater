package com.yelle233.infinitewater;

import com.simibubi.create.api.stress.BlockStressValues;
import com.yelle233.infinitewater.block.InfiniteWaterBlockEntity;
import com.yelle233.infinitewater.config.InfiniteWaterConfig;
import com.yelle233.infinitewater.register.InfiniteWaterBlockRegister;
import com.yelle233.infinitewater.register.InfiniteWaterItemRegister;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod(InfiniteWater.MODID)
public class InfiniteWater {
    public static final String MODID = "infinitewater";


    public InfiniteWater(IEventBus modEventBus, ModContainer modContainer) {
        InfiniteWaterBlockRegister.BLOCKS.register(modEventBus);
        InfiniteWaterItemRegister.ITEMS.register(modEventBus);
        InfiniteWaterTabRegister.TABS.register(modEventBus);
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::commonSetup);
        InfiniteWaterBlockEntity.BLOCK_ENTITY_TYPES.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, InfiniteWaterConfig.SPEC);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                InfiniteWaterBlockEntity.INFINITE_WATER_BLOCK_ENTITY.get(),
                (be, side) -> be.getFluidHandler(side)
        );
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BlockStressValues.IMPACTS.register(
                    InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.get(),
                    () -> InfiniteWaterConfig.STRESS_IMPACT.get()
            );
        });
    }




}
