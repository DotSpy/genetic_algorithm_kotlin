package by.vkiva.model.view.genetic

import by.vkiva.model.view.Reproduction
import kotlin.random.Random

object Crossover {

    fun cross(reproductions: List<Reproduction>, crossoverProbability: Double): List<Chromosome> {
        val shuffled = reproductions.toMutableList().also { it.shuffle() }
        val crossedChromosomes = mutableListOf<Chromosome>()
        for (i in shuffled.indices) {
            if (i + 1 < shuffled.lastIndex && crossoverProbability > Random.nextDouble()) {
                crossedChromosomes.add(mergeChromosome(shuffled[i].chromosome, shuffled[i + 1].chromosome))
            } else {
                crossedChromosomes.add(shuffled[i].chromosome)
            }
        }
        return crossedChromosomes
    }

    private fun mergeChromosome(first: Chromosome, second: Chromosome): Chromosome {
        val randomPosition = Random.nextInt(first.size - 1 - 2) + 1
        return Chromosome(first.value.take(randomPosition).plus(second.value.takeLast(second.size - randomPosition)))
    }

}