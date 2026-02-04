package com.yelle233.infinitewater.ponder;

import com.simibubi.create.AllBlocks;
import com.yelle233.infinitewater.register.InfiniteWaterBlockRegister;
import com.yelle233.infinitewater.config.InfiniteWaterConfig;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class InfiniteWaterBlockPonderScenes {

    public static void basic(SceneBuilder scene, SceneBuildingUtil util) {
        // 从配置读取数值
        double speedThreshold = InfiniteWaterConfig.SPEED_THRESHOLD.get();
        double stressImpact = InfiniteWaterConfig.STRESS_IMPACT.get();

        scene.title("infinite_water", "Infinite Water Source");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos machine = util.grid().at(2, 2, 2);

        scene.world().setBlock(machine, InfiniteWaterBlockRegister.INFINITE_WATER_BLOCK.get().defaultBlockState(), false);
        scene.world().showSection(util.select().position(machine), Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(60)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_1")))
                .placeNearTarget()
                .pointAt(util.vector().centerOf(machine));
        scene.idle(70);

        // ==================== 应力输入说明 ====================

        scene.overlay().showText(60)
                .colored(PonderPalette.MEDIUM)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_2")))
                .placeNearTarget()
                .pointAt(util.vector().topOf(machine));
        scene.idle(70);

        BlockPos shaftAbove = machine.above();
        BlockPos shaftBelow = machine.below();

        scene.world().setBlock(shaftAbove, AllBlocks.SHAFT.getDefaultState()
                .setValue(BlockStateProperties.AXIS, Direction.Axis.Y), false);
        scene.world().showSection(util.select().position(shaftAbove), Direction.DOWN);
        scene.idle(10);

        scene.world().setBlock(shaftBelow, AllBlocks.SHAFT.getDefaultState()
                .setValue(BlockStateProperties.AXIS, Direction.Axis.Y), false);
        scene.world().showSection(util.select().position(shaftBelow), Direction.UP);
        scene.idle(20);

        scene.overlay().showOutline(PonderPalette.GREEN, "top", util.select().position(shaftAbove), 40);
        scene.overlay().showOutline(PonderPalette.GREEN, "bottom", util.select().position(shaftBelow), 40);

        scene.overlay().showText(50)
                .colored(PonderPalette.GREEN)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_3")))
                .placeNearTarget()
                .pointAt(util.vector().topOf(shaftAbove));
        scene.idle(60);

        // 显示应力消耗
        scene.overlay().showText(70)
                .colored(PonderPalette.BLUE)
                .text(String.valueOf(Component.translatable(  "infinitewater.ponder.infinite_water.text_4")))
                .placeNearTarget()
                .pointAt(util.vector().centerOf(machine));
        scene.idle(80);

        // 转速不足提示
        scene.overlay().showText(70)
                .colored(PonderPalette.RED)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_5")))
                .placeNearTarget()
                .pointAt(util.vector().centerOf(machine));
        scene.idle(80);

        // 转速充足提示
        scene.overlay().showText(70)
                .colored(PonderPalette.GREEN)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_6")))
                .placeNearTarget()
                .pointAt(util.vector().centerOf(machine));
        scene.idle(80);

        // ==================== 流体输出说明 ====================

        scene.overlay().showText(60)
                .colored(PonderPalette.BLUE)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_7")))
                .placeNearTarget()
                .pointAt(util.vector().centerOf(machine));
        scene.idle(70);

        BlockPos pipeNorth = machine.north();
        BlockPos pipeSouth = machine.south();
        BlockPos pipeEast = machine.east();
        BlockPos pipeWest = machine.west();

        scene.world().setBlock(pipeNorth, AllBlocks.FLUID_PIPE.getDefaultState(), false);
        scene.world().showSection(util.select().position(pipeNorth), Direction.SOUTH);
        scene.idle(5);

        scene.world().setBlock(pipeSouth, AllBlocks.FLUID_PIPE.getDefaultState(), false);
        scene.world().showSection(util.select().position(pipeSouth), Direction.NORTH);
        scene.idle(5);

        scene.world().setBlock(pipeEast, AllBlocks.FLUID_PIPE.getDefaultState(), false);
        scene.world().showSection(util.select().position(pipeEast), Direction.WEST);
        scene.idle(5);

        scene.world().setBlock(pipeWest, AllBlocks.FLUID_PIPE.getDefaultState(), false);
        scene.world().showSection(util.select().position(pipeWest), Direction.EAST);
        scene.idle(20);

        scene.overlay().showOutline(PonderPalette.BLUE, "sides",
                util.select().position(pipeNorth)
                        .add(util.select().position(pipeSouth))
                        .add(util.select().position(pipeEast))
                        .add(util.select().position(pipeWest)), 60);

        scene.overlay().showText(60)
                .colored(PonderPalette.BLUE)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_8")))
                .placeNearTarget()
                .pointAt(util.vector().centerOf(pipeNorth));
        scene.idle(70);

        BlockPos pump = pipeNorth.north();
        scene.world().setBlock(pump, AllBlocks.MECHANICAL_PUMP.getDefaultState(), false);
        scene.world().showSection(util.select().position(pump), Direction.SOUTH);
        scene.idle(20);

        scene.overlay().showText(60)
                .colored(PonderPalette.BLUE)
                .text(String.valueOf(Component.translatable("infinitewater.ponder.infinite_water.text_9")))
                .placeNearTarget()
                .pointAt(util.vector().centerOf(pump));
        scene.idle(70);



        scene.markAsFinished();
    }
}
