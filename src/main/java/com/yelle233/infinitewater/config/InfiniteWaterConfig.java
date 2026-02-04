package com.yelle233.infinitewater.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class InfiniteWaterConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    // 转速阈值配置
    public static final ModConfigSpec.DoubleValue SPEED_THRESHOLD;
    // 应力消耗配置
    public static final ModConfigSpec.DoubleValue STRESS_IMPACT;

    static {
        BUILDER.push("Infinite Water Block Settings");

        SPEED_THRESHOLD = BUILDER
                .comment("The stress threshold can be adjusted by modifying the speed threshold and the stress consumed per rotation.")
                .comment("Minimum rotation speed (RPM) required to activate the machine")
                .comment("Default: 200 RPM")
                .defineInRange("speedThreshold", 200.0, 0.0, 256.0);

        STRESS_IMPACT = BUILDER
                .comment("Stress consumed per RPM")
                .comment("Default: 5000 SU/RPM")
                .defineInRange("stressImpact", 5000.0, 0.0, 1000000.0);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
