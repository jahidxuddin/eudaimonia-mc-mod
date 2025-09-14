package de.ju.eudaimonia.registry

import de.ju.eudaimonia.Eudaimonia
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object ModItems {
    @JvmStatic
    val CRYSTAL_OF_EXPERIENCE_ITEM: Item = register(
        "crystal_of_experience", { settings -> BlockItem(ModBlocks.CRYSTAL_OF_EXPERIENCE!!, settings) }, Item.Settings()
    )

    @JvmStatic
    fun initialize() {
        CRYSTAL_OF_EXPERIENCE_ITEM
    }

    fun register(
        name: String, itemFactory: (Item.Settings) -> Item, settings: Item.Settings
    ): Item {
        val itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Eudaimonia.MOD_ID, name))
        val item = itemFactory(settings.registryKey(itemKey))
        Registry.register(Registries.ITEM, itemKey, item)
        return item
    }
}
