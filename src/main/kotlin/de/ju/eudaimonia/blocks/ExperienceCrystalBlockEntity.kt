package de.ju.eudaimonia.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class ExperienceCrystalBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(ModBlockEntities.EXPERIENCE_CRYSTAL_BLOCK_ENTITY, pos, state) {

    var storedXp: Int = 0

    fun addXp(amount: Int) {
        storedXp += amount
        markDirty()
    }

    fun removeXp(amount: Int): Boolean {
        return if (storedXp >= amount) {
            storedXp -= amount
            markDirty()
            true
        } else false
    }
}
