package de.ju.eudaimonia.blocks

import com.mojang.serialization.MapCodec
import de.ju.eudaimonia.Eudaimonia
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ExperienceCrystalBlock(settings: Settings) : BlockWithEntity(settings) {
    override fun createBlockEntity(
        pos: BlockPos, state: BlockState
    ): BlockEntity? {
        return ExperienceCrystalBlockEntity(pos, state)
    }

    override fun onUse(
        state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hit: BlockHitResult
    ): ActionResult {
        if (world.isClient) return ActionResult.SUCCESS

        val blockEntity = world.getBlockEntity(pos) as? ExperienceCrystalBlockEntity ?: return ActionResult.FAIL

        if (player.isSneaking) {
            if (player.totalExperience >= 5) {
                player.addExperience(-5)
                blockEntity.addXp(5)
                Eudaimonia.logger.info("amount: ${blockEntity.storedXp}")
            }
        } else {
            if (blockEntity.removeXp(5)) {
                player.addExperience(5)
                Eudaimonia.logger.info("amount: ${blockEntity.storedXp}")
            }
        }

        return ActionResult.SUCCESS
    }

    override fun getCodec(): MapCodec<out BlockWithEntity?>? {
        TODO("Not yet implemented")
    }
}
