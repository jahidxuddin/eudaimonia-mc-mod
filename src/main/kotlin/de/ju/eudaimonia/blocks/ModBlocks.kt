package de.ju.eudaimonia.blocks

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

object ModBlocks {
    @JvmStatic
    val CRYSTAL_OF_EXPERIENCE: Block? = register(
        "crystal_of_experience",
        { settings -> ExperienceCrystalBlock(settings) },
        AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(1.5f).luminance { 7 },
        false
    )

    @JvmStatic
    fun initialize() {
        CRYSTAL_OF_EXPERIENCE
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries? ->
                itemGroup?.add(CRYSTAL_OF_EXPERIENCE?.asItem())
            })
    }

    private fun register(
        name: String,
        blockFactory: (AbstractBlock.Settings) -> Block,
        settings: AbstractBlock.Settings,
        shouldRegisterItem: Boolean
    ): Block {
        val blockKey = keyOfBlock(name)
        val block = blockFactory(settings.registryKey(blockKey))

        if (shouldRegisterItem) {
            val itemKey = keyOfItem(name)
            val blockItem = BlockItem(block, Item.Settings().registryKey(itemKey))
            Registry.register(Registries.ITEM, itemKey, blockItem)
        }

        return Registry.register(Registries.BLOCK, blockKey, block)
    }

    private fun keyOfBlock(name: String): RegistryKey<Block> {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("eudaimonia", name))
    }

    private fun keyOfItem(name: String): RegistryKey<Item> {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of("eudaimonia", name))
    }
}
