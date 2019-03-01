package com.example.examplemod.util

import net.minecraft.block.Block
import net.minecraft.block.state.BlockState
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.World
import net.minecraftforge.registries.ForgeRegistries

val String.toItemStack: ItemStack?
    get() {
        val sect = split(":").toMutableList()
        val item = ForgeRegistries.ITEMS.getValue(ResourceLocation("${sect[0]}:${sect[1]}"))
        //val item = Item.getByNameOrId("${sect[0]}:${sect[1]}")
        return if (item != null) {
            ItemStack(item, 1)
        } else {
            null
        }
    }

val String.toBlock: Block?
    get() {
        val sect = split(":").toMutableList()
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation("${sect[0]}:${sect[1]}"))
    }

val ItemStack.toPretty: String
    get() = item.registryName.toString()

val IBlockState.toPretty: String
    get() = block.registryName.toString()

fun BlockPos.adjacentNeighbors(world: IWorld): List<BlockPos> {
    return listOf(
            north(),
            east(),
            south(),
            west()
    )
}

fun BlockPos.diagonalNeighbors(world: IWorld): List<BlockPos> {
    return listOf(
            north().east(),
            south().east(),
            south().west(),
            north().west()
    )
}

fun BlockPos.allNeighbors(world: IWorld): List<BlockPos> {
    return adjacentNeighbors(world) + diagonalNeighbors(world)
}