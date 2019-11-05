package by.vkiva.model.view.genetic

import by.vkiva.model.view.Plot

class GeneticAlgorithm(
    private val chromosomeCount: Int,
    private val chromosomeLength: Int,
    private val crossProbability: Double,
    private val mutationProbability: Double,
    private val maxGenerationNumber: Int
) {

    private val population: MutableList<Chromosome> = mutableListOf()

    fun solve(oldPopulation: List<Chromosome>): List<List<Chromosome>> {
        if (oldPopulation.isEmpty()) {
            for (i in 1..chromosomeCount) {
                population[i] = RandomChromosomeCreator.createRandomChromosome(chromosomeLength)
            }
        }
//feat function
        return emptyList()
    }
}