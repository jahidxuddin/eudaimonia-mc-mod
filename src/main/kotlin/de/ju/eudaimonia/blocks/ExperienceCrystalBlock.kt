package de.ju.eudaimonia.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ExperienceCrystalBlock(settings: Settings) : Block(settings) {
    override fun onUse(
        state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            player.addExperience(5)
        }

        return ActionResult.SUCCESS
    }
}
