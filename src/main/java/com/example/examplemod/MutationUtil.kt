package com.example.examplemod

import com.example.examplemod.util.Mutation
import com.example.examplemod.util.allNeighbors
import com.example.examplemod.util.toBlock
import com.example.examplemod.util.toPretty
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import java.util.*

object MutationUtil {

    private val mutationRNG = Random()

    fun spreadMutationAttempt(world: IWorld, state: IBlockState, pos: BlockPos) {
        // Attempt mutation on all neighboring air blocks
        pos.allNeighbors(world).filter {
            world.getBlockState(it).toPretty == "minecraft:air"
        }.map {
            attemptMutation(world, world.getBlockState(it), it)
        }
    }

    private fun attemptMutation(world: IWorld, state: IBlockState, pos: BlockPos) {
        val neighborStates = pos.allNeighbors(world).map { world.getBlockState(it) }
        val names = neighborStates.map { it.toPretty }
        val uniqueNames = names.toSet()
        val numChecks = names.size / uniqueNames.size
        val validMutations = MutationRegistry.content.filter { uniqueNames.containsAll(it.inputs) }.shuffled()
        for (checkNum in 0 until numChecks) {
            if (tryMutate(validMutations, world, pos)) {
                break
            }
        }
    }

    private fun tryMutate(mutations: List<Mutation>, world: IWorld, pos: BlockPos): Boolean {
        for (mutation in mutations) {
            if (mutationRNG.nextDouble() < mutation.chance) {
                plant(mutation, world, pos)
                return true
            }
        }
        return false
    }

    private fun plant(mutation: Mutation, world: IWorld, pos: BlockPos) {
        if (world.getBlockState(pos.down()).toPretty == "minecraft:farmland") {
            world.setBlockState(pos, mutation.output.toBlock?.defaultState ?: Blocks.AIR.defaultState, 3)
        }
    }

}