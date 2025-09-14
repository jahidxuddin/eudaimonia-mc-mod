package de.ju.eudaimonia

import de.ju.eudaimonia.registry.ModBlockEntities
import de.ju.eudaimonia.registry.ModBlocks
import de.ju.eudaimonia.registry.ModItems
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Eudaimonia : ModInitializer {
    const val MOD_ID: String = "eudaimonia"
    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("$MOD_ID mod initialized.")
        ModBlocks.initialize()
        ModBlockEntities.initialize()
        ModItems.initialize()
    }
}
