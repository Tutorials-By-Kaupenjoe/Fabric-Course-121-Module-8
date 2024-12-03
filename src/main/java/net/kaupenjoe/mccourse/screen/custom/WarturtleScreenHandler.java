package net.kaupenjoe.mccourse.screen.custom;

import net.kaupenjoe.mccourse.entity.custom.WarturtleEntity;
import net.kaupenjoe.mccourse.item.custom.WarturtleArmorItem;
import net.kaupenjoe.mccourse.screen.ModScreenHandlers;
import net.minecraft.block.Block;
import net.minecraft.block.DyedCarpetBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.List;
import java.util.UUID;

public class WarturtleScreenHandler extends ScreenHandler {
    private Inventory warturtleContainer;
    public WarturtleEntity warturtle;

    // With Help from https://github.com/Mrbysco/ChocoCraft4/tree/arch/1.21
    // Under MIT LICENSE
    public static WarturtleScreenHandler create(int i, PlayerInventory inventory, UUID uuid) {
        List<WarturtleEntity> turtles = inventory.player.getWorld().getEntitiesByClass(WarturtleEntity.class,
                inventory.player.getBoundingBox().expand(16), test -> test.getUuid().equals(uuid));
        WarturtleEntity warturtleEntity = turtles.isEmpty() ? null : turtles.getFirst();
        return new WarturtleScreenHandler(i, inventory, new SimpleInventory(28), warturtleEntity, 4);
    }

    public WarturtleScreenHandler(int containerId, PlayerInventory inventory, Inventory warturtleContainer, final WarturtleEntity warturtleEntity, int columns) {
        super(ModScreenHandlers.WARTURTLE_SCREEN_HANDLER, containerId);
        this.warturtleContainer = warturtleContainer;
        this.warturtle = warturtleEntity;
        warturtleContainer.onOpen(inventory.player);

        // Armor Slot
        this.addSlot(new Slot(warturtleContainer, 0, 8, 63) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof WarturtleArmorItem;
            }
        });
        // Dye Slot
        this.addSlot(new Slot(warturtleContainer, 1, 44, 63) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return warturtleEntity.hasArmorOn() && Block.getBlockFromItem(stack.getItem()) instanceof DyedCarpetBlock;
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        // Chest Slot Tier 1
        this.addSlot(new Slot(warturtleContainer, 2, 72, 27) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.CHEST);
            }

            @Override
            public boolean canTakeItems(PlayerEntity player) {
                return !warturtleEntity.hasTier2Chest();
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        // Chest Slot Tier 2
        this.addSlot(new Slot(warturtleContainer, 3, 72, 45) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.CHEST) && warturtleEntity.hasTier1Chest();
            }

            @Override
            public boolean canTakeItems(PlayerEntity player) {
                return !warturtleEntity.hasTier3Chest();
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        // Chest Slot Tier 3
        this.addSlot(new Slot(warturtleContainer, 4, 72, 63) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.CHEST) && warturtleEntity.hasTier2Chest();
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        if (columns > 0) {
            for (int l = 0; l < columns; l++) {
                this.addSlot(new Slot(warturtleContainer, 5 + l, 98 + l * 18, 27) {
                    @Override
                    public boolean isEnabled() {
                        return warturtleEntity.hasTier1Chest();
                    }
                });
            }
            for (int l = 0; l < columns; l++) {
                this.addSlot(new Slot(warturtleContainer, 5 + l + columns, 98 + l * 18, 27 + 18){
                    @Override
                    public boolean isEnabled() {
                        return warturtleEntity.hasTier2Chest();
                    }
                });
            }
            for (int l = 0; l < columns; l++) {
                this.addSlot(new Slot(warturtleContainer, 5 + l + 2 * columns, 98 + l * 18, 27 + 2 * 18){
                    @Override
                    public boolean isEnabled() {
                        return warturtleEntity.hasTier3Chest();
                    }
                });
            }
        }


        for (int i1 = 0; i1 < 3; i1++) {
            for (int k1 = 0; k1 < 9; k1++) {
                this.addSlot(new Slot(inventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18 + -18));
            }
        }

        for (int j1 = 0; j1 < 9; j1++) {
            this.addSlot(new Slot(inventory, j1, 8 + j1 * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return !this.warturtle.areInventoriesDifferent(this.warturtleContainer) &&
                this.warturtleContainer.canPlayerUse(player) &&
                this.warturtle.isAlive() && player.canInteractWithEntity(this.warturtle, 4.0);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = (Slot)this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            int i = this.warturtleContainer.size() + 1;
            if (slot < i) {
                if (!this.insertItem(itemStack2, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).canInsert(itemStack2) && !this.getSlot(1).hasStack()) {
                if (!this.insertItem(itemStack2, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).canInsert(itemStack2)) {
                if (!this.insertItem(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 1 || !this.insertItem(itemStack2, 2, i, false)) {
                int k;
                int j = i;
                int l = k = j + 27;
                int m = l + 9;
                if (slot >= l && slot < m ? !this.insertItem(itemStack2, j, k, false) : (slot >= j && slot < k ? !this.insertItem(itemStack2, l, m, false) : !this.insertItem(itemStack2, l, k, false))) {
                    return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
        }
        return itemStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.warturtleContainer.onClose(player);
    }
}
