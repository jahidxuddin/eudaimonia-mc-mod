package de.ju.eudaimonia

import de.ju.eudaimonia.blocks.ModBlockEntities
import de.ju.eudaimonia.blocks.ModBlocks
import de.ju.eudaimonia.items.ModItems
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Eudaimonia : ModInitializer {
    val logger: Logger = LoggerFactory.getLogger("eudaimonia")

	override fun onInitialize() {
		logger.info("Eudaimonia mod initialized.")
		ModBlocks.initialize()
		ModBlockEntities.initialize()
		ModItems.initialize()
	}
}
