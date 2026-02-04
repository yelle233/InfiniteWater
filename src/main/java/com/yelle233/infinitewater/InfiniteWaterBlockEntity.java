package com.yelle233.infinitewater;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class InfiniteWaterBlockEntity extends KineticBlockEntity {


    private boolean isRunningCached = false;

    public InfiniteWaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(INFINITE_WATER_BLOCK_ENTITY.get(), pos, blockState);
    }
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, InfiniteWater.MODID);

    public static final Supplier<BlockEntityType<InfiniteWaterBlockEntity>> INFINITE_WATER_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "infinite_water_block_entity",
            () -> BlockEntityType.Builder.of(
                            InfiniteWaterBlockEntity::new,
                            InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.get()
                    )
                    .build(null)
    );

    private final IFluidHandler handler = new IFluidHandler() {

        @Override
        public int getTanks() { return 1; }

        @Override
        public FluidStack getFluidInTank(int tank) {
            if (!isRunning())
                return FluidStack.EMPTY;
            return new FluidStack(Fluids.WATER, Integer.MAX_VALUE);
        }

        @Override
        public int getTankCapacity(int tank) { return Integer.MAX_VALUE; }

        @Override
        public boolean isFluidValid(int tank, FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }

        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return 0; // 不接受灌入
        }

        @Override
        public FluidStack drain(FluidStack resource, FluidAction action) {
            if (resource.getFluid() != Fluids.WATER) return FluidStack.EMPTY;
            return drain(resource.getAmount(), action);
        }

        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            if (!isRunning()) return FluidStack.EMPTY;
            if (maxDrain <= 0) return FluidStack.EMPTY;
            return new FluidStack(Fluids.WATER, maxDrain);
        }
    };

    private boolean isRunning() {
        return hasEnoughRemainingStress();
    }

    public IFluidHandler getFluidHandler(@Nullable Direction side) {
        return handler;
    }

    @Override
    public float calculateStressApplied() {
        float impact = InfiniteWaterConfig.STRESS_IMPACT.get().floatValue();
        this.lastStressApplied=impact;
        return impact;
    }


    private boolean hasEnoughRemainingStress() {
        if (level == null) return false;
        if (level.isClientSide) {
            return isRunningCached;  // 客户端使用缓存值
        }
        if(InfiniteWaterConfig.STRESS_IMPACT.get().floatValue()==0.0){
            return true;
        }
        float threshold = InfiniteWaterConfig.SPEED_THRESHOLD.get().floatValue();
        return Math.abs(getSpeed()) >= threshold;
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.write(tag, registries, clientPacket);
        if (clientPacket) {
            tag.putBoolean("Infinite_Water_Running", hasEnoughRemainingStress());
        }
    }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        super.read(tag, registries, clientPacket);
        if (clientPacket) {
            isRunningCached = tag.getBoolean("Infinite_Water_Running");
        }
    }


    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        int before = tooltip.size();
        float currentSpeed = Math.abs(getSpeed());
        float threshold = InfiniteWaterConfig.SPEED_THRESHOLD.get().floatValue();
        boolean active = currentSpeed >= threshold;
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        if(!active) {
            if(InfiniteWaterConfig.SPEED_THRESHOLD.get().floatValue()>0&&InfiniteWaterConfig.STRESS_IMPACT.get().floatValue()>0){
                tooltip.add(Component.empty());
                tooltip.add(Component.literal("    " + Component.translatable("infinitewater.infinite_water_block.tooltip2").getString()+" §b"+String.format("%.0f", InfiniteWaterConfig.SPEED_THRESHOLD.get().floatValue()*InfiniteWaterConfig.STRESS_IMPACT.get().floatValue()) + " §bsu"));
                tooltip.add(Component.literal("    " + Component.translatable("infinitewater.infinite_water_block.tooltip1").getString()));
            }

        }
        return tooltip.size() > before;
    }

    @Override
    public void tick() {
        super.tick();
        if (level != null && !level.isClientSide) {
            boolean running = hasEnoughRemainingStress();
            if (running != isRunningCached) {
                isRunningCached = running;
                sendData();  // 触发同步
            }
        }
    }

}
