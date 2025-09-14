package de.ju.eudaimonia.experiencecrystal

import com.mojang.serialization.MapCodec
import de.ju.eudaimonia.Eudaimonia
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
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

        val amount = 5
        when {
            player.isSneaking && player.totalExperience >= amount -> {
                player.addExperience(-amount)
                blockEntity.addXp(amount)
            }

            !player.isSneaking && blockEntity.removeXp(amount) -> {
                world.playSound(
                    null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f
                )
                player.addExperience(amount)
            }
        }

        Eudaimonia.logger.info("stored xp: " + blockEntity.storedXp)

        return ActionResult.SUCCESS
    }

    override fun onBreak(
        world: World, pos: BlockPos, state: BlockState, player: PlayerEntity
    ): BlockState {
        if (!world.isClient && !player.isCreative) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is ExperienceCrystalBlockEntity) {
                val xpAmount = blockEntity.storedXp
                ExperienceOrbEntity.spawn(world as ServerWorld?, pos.toCenterPos(), xpAmount)
            }
            dropStack(world, pos, ItemStack(this.asItem()))
        }
        return super.onBreak(world, pos, state, player)
    }

    override fun getCodec(): MapCodec<out BlockWithEntity?>? {
        TODO("Not yet implemented")
    }
}
