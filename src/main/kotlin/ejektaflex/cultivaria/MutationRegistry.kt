package ejektaflex.cultivaria

import ejektaflex.cultivaria.util.Mutation
import ejektaflex.cultivaria.util.ValueRegistry

object MutationRegistry : ValueRegistry<Mutation>() {
    val defaultData = ValueRegistry<Mutation>().apply {
        // Mutation Chance, Output, Inputs
        add(
                Mutation("minecraft:wheat", 1.0, "minecraft:carrots", "minecraft:potatoes")
        )
    }
}