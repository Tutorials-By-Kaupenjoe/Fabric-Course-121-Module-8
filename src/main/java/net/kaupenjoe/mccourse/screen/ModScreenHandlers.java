package net.kaupenjoe.mccourse.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.screen.custom.WarturtleScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

public class ModScreenHandlers {
    public static final ScreenHandlerType<WarturtleScreenHandler> WARTURTLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "warturtle_screen_handler"),
                    new ExtendedScreenHandlerType<>(WarturtleScreenHandler::create, Uuids.PACKET_CODEC));


    public static void registerScreenHandler() {
        MCCourseMod.LOGGER.info("Registering Screen Handlers for " + MCCourseMod.MOD_ID);
    }
}
