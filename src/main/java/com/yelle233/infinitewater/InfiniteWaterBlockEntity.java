package com.yelle233.infinitewater;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class InfiniteWaterBlockEntity extends KineticBlockEntity {

//    private float lastImpact = -1;
    private static final float SPEED_THRESHOLD = 200f;

    private float cachedRemainingStress = 0;
    private boolean stressCacheValid = false;
    private boolean canOutputClient = false;


    public InfiniteWaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(INFINITE_WATER_BLOCK_ENTITY.get(), pos, blockState);
    }
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, InfiniteWater.MODID);

    public static final Supplier<BlockEntityType<InfiniteWaterBlockEntity>> INFINITE_WATER_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "infinite_water_block_entity",
            // 方块实体类型，使用构建器创建。
            () -> BlockEntityType.Builder.of(
                            // 用于构造方块实体实例的供应商。
                            InfiniteWaterBlockEntity::new,
                            // 可以具有此方块实体的方块的变长参数。
                            // 假设引用的方块是 DeferredBlock<Block>。
                            InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.get()
                    )
                    // 使用 null 构建；原版对参数进行了一些数据修复操作，我们不需要。
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
        this.lastStressApplied=5000.0f;
        return 5000.0f;
    }


    private boolean hasEnoughRemainingStress() {
        if (level == null || level.isClientSide) return false;
        if (getSpeed() == 0 || isOverStressed()) return false;
        return Math.abs(getSpeed()) >= SPEED_THRESHOLD;
    }

    @Override
    public boolean addToTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        float currentSpeed = Math.abs(getSpeed());
        boolean active = currentSpeed >= SPEED_THRESHOLD;
        if(!active) {
            tooltip.add(Component.literal("显然转速不足，无法输出") );
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }

}
