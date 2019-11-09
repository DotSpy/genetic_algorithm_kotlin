package by.vkiva.model.view.genetic

import kotlin.random.Random

object Mutator {

    fun mutate(chromosomes: List<Chromosome>, chanceToMutate: Double): List<Chromosome> {
        val mutatedChromosomes = mutableListOf<Chromosome>()
        for (chromosome in chromosomes) {
            for (i in chromosome.value.indices) {
                if (chanceToMutate > Random.nextDouble()) {
                    chromosome.value[i] = chromosome.value[i].not()
                }
            }
            mutatedChromosomes.add(chromosome)
        }
        return chromosomes
    }
}