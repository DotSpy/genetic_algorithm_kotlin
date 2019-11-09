package by.vkiva.model.view.controller

import by.vkiva.model.view.Population
import by.vkiva.model.view.genetic.Chromosome
import by.vkiva.model.view.genetic.GeneticAlgorithm
import tornadofx.Controller

object GeneticController : Controller() {

    fun calculate(
        chromosomeCount: Int,
        chromosomeLength: Int,
        crossProbability: Double,
        mutationProbability: Double,
        maxGenerationNumber: Int,
        oldGeneration: List<Chromosome>
    ): Population =
        GeneticAlgorithm(
            chromosomeCount,
            chromosomeLength,
            crossProbability,
            mutationProbability
        ).solve(oldGeneration)

}