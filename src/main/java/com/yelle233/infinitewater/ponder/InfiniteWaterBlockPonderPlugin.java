package com.yelle233.infinitewater.ponder;

import com.yelle233.infinitewater.InfiniteWater;
import com.yelle233.infinitewater.register.InfiniteWaterBlockRegister;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class InfiniteWaterBlockPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return InfiniteWater.MODID;
    }
    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        // 如果你用的是 RegistryEntry / DeferredHolder，也可以像示例那样 withKeyFunction(...)
        helper.forComponents(
            InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.getId()
        ).addStoryBoard("infinite_water_block", InfiniteWaterBlockPonderScenes::basic);
    }

}
