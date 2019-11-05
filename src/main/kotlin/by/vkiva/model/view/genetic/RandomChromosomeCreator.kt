package by.vkiva.model.view.genetic

object RandomChromosomeCreator {

    fun createRandomChromosome(size: Int): Chromosome {
        val chromosome = Chromosome(size)
        for (i in 0..chromosome.value.size) {
            if (Math.random() > 0.5) {
                chromosome.value[i] = true
            }
        }
        return chromosome
    }
}