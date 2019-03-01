package com.example.examplemod.util

class Mutation(val chance: Double = 1.0, val output: String, val inputs: Set<String>) {
    constructor(chanceIn: Double, outputStr: String, vararg inputStrs: String) : this(chanceIn, outputStr, inputStrs.toSet())
}