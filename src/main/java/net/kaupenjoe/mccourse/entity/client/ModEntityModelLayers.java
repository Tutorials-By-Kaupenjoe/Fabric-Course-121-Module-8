package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModEntityModelLayers {
    public static final EntityModelLayer DODO =
            new EntityModelLayer(Identifier.of(MCCourseMod.MOD_ID, "dodo"), "main");

    public static final EntityModelLayer GIRAFFE =
            new EntityModelLayer(Identifier.of(MCCourseMod.MOD_ID, "giraffe"), "main");
}
