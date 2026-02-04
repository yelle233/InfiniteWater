package com.yelle233.infinitewater;

import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.yelle233.infinitewater.block.InfiniteWaterBlockEntity;
import com.yelle233.infinitewater.ponder.InfiniteWaterBlockPonderPlugin;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.

// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = InfiniteWater.MODID, value = Dist.CLIENT)
public class InfiniteWaterClient {
    public InfiniteWaterClient(ModContainer container) {

        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            SimpleBlockEntityVisualizer
                    .builder(InfiniteWaterBlockEntity.INFINITE_WATER_BLOCK_ENTITY.get())
                    .factory((ctx, be, pt) -> SingleAxisRotatingVisual.shaft(ctx, be, pt))
                    .apply();
        });
        PonderIndex.addPlugin(new InfiniteWaterBlockPonderPlugin());
    }
}
