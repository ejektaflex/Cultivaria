package com.example.examplemod

import com.example.examplemod.util.Mutation
import com.example.examplemod.util.ValueRegistry

object MutationRegistry : ValueRegistry<Mutation>() {
    val defaultData = ValueRegistry<Mutation>().apply {
        // Mutation Chance, Output, Inputs
        add(
                Mutation(1.0, "minecraft:wheat", "minecraft:carrots", "minecraft:potatoes")
        )
    }
}