package net.kaupenjoe.mccourse.entity;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.DodoEntity;
import net.kaupenjoe.mccourse.entity.custom.GiraffeEntity;
import net.kaupenjoe.mccourse.entity.custom.TomahawkProjectileEntity;
import net.kaupenjoe.mccourse.entity.custom.WarturtleEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<DodoEntity> DODO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MCCourseMod.MOD_ID, "dodo"),
            EntityType.Builder.create(DodoEntity::new, SpawnGroup.CREATURE).dimensions(1f, 2.5f).build());

    public static final EntityType<GiraffeEntity> GIRAFFE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MCCourseMod.MOD_ID, "giraffe"),
            EntityType.Builder.create(GiraffeEntity::new, SpawnGroup.CREATURE).dimensions(1.5f, 2.5f).build());

    public static final EntityType<WarturtleEntity> WARTURTLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MCCourseMod.MOD_ID, "warturtle"),
            EntityType.Builder.create(WarturtleEntity::new, SpawnGroup.CREATURE).dimensions(2.5f, 1.5f).build());


    public static final EntityType<TomahawkProjectileEntity> TOMAHAWK = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MCCourseMod.MOD_ID, "tomahawk"),
            EntityType.Builder.<TomahawkProjectileEntity>create(TomahawkProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());


    public static void registerModEntities() {
        MCCourseMod.LOGGER.info("Registering Mod Entities for " + MCCourseMod.MOD_ID);
    }
}
