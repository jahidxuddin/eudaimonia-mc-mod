package de.ju.eudaimonia.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.storage.ReadView
import net.minecraft.storage.WriteView
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

    override fun writeData(view: WriteView) {
        super.writeData(view)
        view.putInt("StoredXp", storedXp)
    }

    override fun readData(view: ReadView) {
        super.readData(view)
        storedXp = view.getInt("StoredXp", 0)
    }
}
