package by.vkiva.model.view.genetic

object RandomChromosomeCreator {

    fun createRandomChromosome(size: Int): Chromosome {
        val chromosome = Chromosome(size)
        val value = BooleanArray(size)
        for (i in 0..chromosome.value.lastIndex) {
            if (Math.random() > 0.5) {
                value[i] = true
            }
        }
        return Chromosome(value)
    }
}