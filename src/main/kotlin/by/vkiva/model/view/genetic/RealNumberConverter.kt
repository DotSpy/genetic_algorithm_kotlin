package by.vkiva.model.view.genetic

import kotlin.math.pow

object RealNumberConverter {

    fun convertToRealNumber(
        chromosome: Chromosome,
        lowerBorder: Long,
        highestBorder: Long
    ): Double =
        lowerBorder + (chromosome.getAsInt() * (highestBorder - lowerBorder) / (2.0.pow(chromosome.value.size.toDouble()) - 1))

}