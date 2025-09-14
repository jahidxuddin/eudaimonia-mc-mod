package de.ju.eudaimonia.registry

import de.ju.eudaimonia.Eudaimonia
import de.ju.eudaimonia.experiencecrystal.ExperienceCrystalBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModBlockEntities {
    @JvmStatic
    @Suppress("DEPRECATION")
    val EXPERIENCE_CRYSTAL_BLOCK_ENTITY: BlockEntityType<ExperienceCrystalBlockEntity> = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        identifier("experience_crystal_block_entity"),
        FabricBlockEntityTypeBuilder.create(
            ::ExperienceCrystalBlockEntity, ModBlocks.CRYSTAL_OF_EXPERIENCE
        ).build(null)
    )

    @JvmStatic
    fun initialize() {
        EXPERIENCE_CRYSTAL_BLOCK_ENTITY
    }

    private fun identifier(path: String): Identifier = Identifier.of(Eudaimonia.MOD_ID, path)
}