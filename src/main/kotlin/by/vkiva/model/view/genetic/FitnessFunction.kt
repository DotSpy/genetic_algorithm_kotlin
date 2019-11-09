package by.vkiva.model.view.genetic

import by.vkiva.model.view.Plot
import by.vkiva.model.view.Population
import by.vkiva.model.view.Reproduction

class FitnessFunction(
    private val mutateChance: Double,
    private val crossoverProbability: Double
) {

    fun fit(chromosomes: List<Chromosome>): Population =
        Function().produceNextGeneration(chromosomes, mutateChance, crossoverProbability)

    private class Function {

        private val reproductionTable = mutableListOf<Reproduction>()
        private var middleValue = 0.0
        private var sumOfFitness = 0.0
        private var maxOfFitness = Double.MIN_VALUE

        fun produceNextGeneration(
            chromosomes: List<Chromosome>,
            mutateChance: Double,
            crossoverProbability: Double
        ): Population {
            for (chromosome in chromosomes) {
                val realNumber =
                    RealNumberConverter.convertToRealNumber(chromosome, Plot.T_MIN.toLong(), Plot.T_MAX.toLong())
                val yCorrectedValue = Plot.getYValueForX(realNumber) - Plot.T_MIN
                sumOfFitness += yCorrectedValue
                if (realNumber > maxOfFitness) {
                    maxOfFitness = realNumber
                }
                reproductionTable.add(Reproduction(chromosome, yCorrectedValue))
            }
            middleValue = sumOfFitness / chromosomes.size
            for (reproduction in reproductionTable) {
                val normalizedValue = reproduction.fitnessValue / sumOfFitness
                reproduction.normalizedValue = normalizedValue
                reproduction.expectedChromosomeCount = normalizedValue * chromosomes.size
            }
            val newGeneration = mutableListOf<Reproduction>()
            for (i in 1..chromosomes.size) {
                newGeneration.add(getRandomWeightedReproduction())
            }
            val crossedGeneration = Crossover.cross(newGeneration, crossoverProbability)
            val mutatedChromosomes = Mutator.mutate(crossedGeneration, mutateChance)
            val bestAndMiddleValueOfFitness = getBestAndMiddleValueOfFitness()
            return Population(mutatedChromosomes, bestAndMiddleValueOfFitness.first, bestAndMiddleValueOfFitness.second)
        }

        private fun getBestAndMiddleValueOfFitness(): Pair<Double, Double> {
            var bestFitness = Double.MIN_VALUE
            var middleFitness = 0.0
            for (reproduction in reproductionTable) {
                if (reproduction.fitnessValue > bestFitness) {
                    bestFitness = reproduction.fitnessValue
                }
                middleFitness += reproduction.fitnessValue
            }
            return Pair(bestFitness, middleFitness / reproductionTable.size)
        }

        private fun getRandomWeightedReproduction(): Reproduction {
            var totalWeight = 0.0
            for (value in reproductionTable) {
                totalWeight += value.normalizedValue!!
            }
            var random = Math.random() * totalWeight
            for (i in reproductionTable.indices) {
                random -= reproductionTable[i].normalizedValue!!
                if (random <= 0.0) {
                    return reproductionTable[i]
                }
            }
            throw IllegalStateException("Couldn't choose random value for table $reproductionTable")
        }
    }
}