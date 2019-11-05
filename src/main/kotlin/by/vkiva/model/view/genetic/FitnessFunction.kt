package by.vkiva.model.view.genetic

import by.vkiva.model.view.Plot
import by.vkiva.model.view.Reproduction


class FitnessFunction {

    fun fit(chromosomes: List<Chromosome>): List<Chromosome> =
        Function().produceNextGeneration(chromosomes)

    private class Function {

        private val valueToChromosomeMap = mutableMapOf<Double, Chromosome>()
        private val reproductionTable = mutableListOf<Reproduction>()
        private var middleValue = 0.0
        private var sumOfFitness = 0.0
        private var maxOfFitness = Double.MIN_VALUE

        fun produceNextGeneration(chromosomes: List<Chromosome>): List<Chromosome> {
            for (chromosome in chromosomes) {
                val realNumber =
                    RealNumberConverter.convertToRealNumber(chromosome, Plot.T_MIN.toLong(), Plot.T_MAX.toLong())
                valueToChromosomeMap[Plot.getValueForX(realNumber)] = chromosome
                sumOfFitness += realNumber
                if (realNumber > maxOfFitness) {
                    maxOfFitness = realNumber
                }
                reproductionTable.add(Reproduction(chromosome, Plot.getValueForX(realNumber)))
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
            val randomWeightedReproduction = getRandomWeightedReproduction()

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

// Now choose a random item
        int randomIndex = -1;
        for (int i = 0; i < items.length; ++i)
        {
            random -= items[i].getWeight();
            if (random <= 0.0 d) {
                randomIndex = i;
                break;
            }
        }
        Item myRandomItem = items[randomIndex];
    }
}