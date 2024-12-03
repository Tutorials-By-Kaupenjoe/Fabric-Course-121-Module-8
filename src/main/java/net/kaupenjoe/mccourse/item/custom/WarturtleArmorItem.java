package net.kaupenjoe.mccourse.item.custom;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;

public class WarturtleArmorItem extends ArmorItem {
    public WarturtleArmorItem(RegistryEntry<ArmorMaterial> material, Settings settings) {
        super(material, Type.BODY, settings);
    }
}
