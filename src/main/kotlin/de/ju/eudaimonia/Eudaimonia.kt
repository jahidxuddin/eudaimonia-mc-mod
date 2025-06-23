package de.ju.eudaimonia

import de.ju.eudaimonia.blocks.ModBlocks
import de.ju.eudaimonia.items.ModItems
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Eudaimonia : ModInitializer {
    private val logger = LoggerFactory.getLogger("eudaimonia")

	override fun onInitialize() {
		logger.info("Eudaimonia mod initialized.")
		ModBlocks.initialize()
		ModItems.initialize()
	}
}
