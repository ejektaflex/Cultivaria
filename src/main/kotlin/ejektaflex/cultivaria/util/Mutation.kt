package ejektaflex.cultivaria.util

class Mutation(val output: String, val chance: Double = 1.0, val inputs: Set<String>) {
    constructor(outputStr: String, chanceIn: Double, vararg inputStrs: String) : this(outputStr, chanceIn, inputStrs.toSet())
}