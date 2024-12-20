package net.kaupenjoe.mccourse.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.item.custom.ChainsawItem;
import net.kaupenjoe.mccourse.item.custom.TomahawkItem;
import net.kaupenjoe.mccourse.item.custom.WarturtleArmorItem;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    public static final Item FLUORITE = registerItem("fluorite", new Item(new Item.Settings()));
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", new Item(new Item.Settings()));

    public static final Item CHAINSAW = registerItem("chainsaw", new ChainsawItem(new Item.Settings().maxDamage(32)));
    public static final Item STRAWBERRY = registerItem("strawberry", new Item(new Item.Settings().food(ModFoodComponents.STRAWBERRY)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.mccourse.strawberry.tooltip.1"));

            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings()));

    public static final Item DODO_SPAWN_EGG = registerItem("dodo_spawn_egg",
            new SpawnEggItem(ModEntities.DODO, 0x465ae0, 0x545978, new Item.Settings()));
    public static final Item GIRAFFE_SPAWN_EGG = registerItem("giraffe_spawn_egg",
            new SpawnEggItem(ModEntities.GIRAFFE, 0xe7d7a5, 0x7e5b41, new Item.Settings()));
    public static final Item WARTURTLE_SPAWN_EGG = registerItem("warturtle_spawn_egg",
            new SpawnEggItem(ModEntities.WARTURTLE, 0xa86518, 0x3b260f, new Item.Settings()));

    public static final Item TOMAHAWK = registerItem("tomahawk",
            new TomahawkItem(new Item.Settings().maxCount(16)));

    public static final Item IRON_WARTURTLE_ARMOR = registerItem("iron_warturtle_armor",
            new WarturtleArmorItem(ArmorMaterials.IRON, new Item.Settings().maxDamage(200)));
    public static final Item GOLD_WARTURTLE_ARMOR = registerItem("gold_warturtle_armor",
            new WarturtleArmorItem(ArmorMaterials.GOLD, new Item.Settings().maxDamage(400)));
    public static final Item DIAMOND_WARTURTLE_ARMOR = registerItem("diamond_warturtle_armor",
            new WarturtleArmorItem(ArmorMaterials.DIAMOND, new Item.Settings().maxDamage(600)));
    public static final Item NETHERITE_WARTURTLE_ARMOR = registerItem("netherite_warturtle_armor",
            new WarturtleArmorItem(ArmorMaterials.NETHERITE, new Item.Settings().maxDamage(800)));
    public static final Item FLUORITE_WARTURTLE_ARMOR = registerItem("fluorite_warturtle_armor",
            new WarturtleArmorItem(ArmorMaterials.IRON, new Item.Settings().maxDamage(1000)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MCCourseMod.MOD_ID, name), item);
    }

    private static void customIngredients(FabricItemGroupEntries entries) {
        entries.add(FLUORITE);
        entries.add(RAW_FLUORITE);
    }

    public static void registerModItems() {
        MCCourseMod.LOGGER.info("Registering Mod Items for " + MCCourseMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }
}
