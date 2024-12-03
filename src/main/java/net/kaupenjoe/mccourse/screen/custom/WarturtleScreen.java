package net.kaupenjoe.mccourse.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.WarturtleEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WarturtleScreen extends HandledScreen<WarturtleScreenHandler> {
    private static final Identifier GUI_TEXTURE_T0 =
            Identifier.of(MCCourseMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier0.png");
    private static final Identifier GUI_TEXTURE_T1 =
            Identifier.of(MCCourseMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier1.png");
    private static final Identifier GUI_TEXTURE_T2 =
            Identifier.of(MCCourseMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier2.png");
    private static final Identifier GUI_TEXTURE_T3 =
            Identifier.of(MCCourseMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier3.png");
    private final WarturtleEntity warturtle;
    private float xMouse;
    private float yMouse;

    public WarturtleScreen(WarturtleScreenHandler pMenu, PlayerInventory pPlayerInventory, Text title) {
        super(pMenu, pPlayerInventory, title);
        this.warturtle = pMenu.warturtle;
    }

    @Override
    protected void init() {
        super.init();
        titleX = 72;
        titleY = 12;

        playerInventoryTitleX = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        if(!warturtle.hasTier1Chest()) {
            RenderSystem.setShaderTexture(0, GUI_TEXTURE_T0);
            context.drawTexture(GUI_TEXTURE_T0, x, y, 0, 0, backgroundWidth, backgroundHeight);
        } else if(warturtle.hasTier1Chest()) {
            RenderSystem.setShaderTexture(0, GUI_TEXTURE_T1);
            context.drawTexture(GUI_TEXTURE_T1, x, y, 0, 0, backgroundWidth, backgroundHeight);
        }

        if(warturtle.hasTier2Chest()) {
            RenderSystem.setShaderTexture(0, GUI_TEXTURE_T2);
            context.drawTexture(GUI_TEXTURE_T2, x, y, 0, 0, backgroundWidth, backgroundHeight);
        }
        if(warturtle.hasTier3Chest()) {
            RenderSystem.setShaderTexture(0, GUI_TEXTURE_T3);
            context.drawTexture(GUI_TEXTURE_T3, x, y, 0, 0, backgroundWidth, backgroundHeight);
        }

        InventoryScreen.drawEntity(context, x + 8, y + 9, x + 60, y + 58, 20, 0.05F,
                this.xMouse, this.yMouse, this.warturtle);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.xMouse = (float)mouseX;
        this.yMouse = (float)mouseY;

        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
