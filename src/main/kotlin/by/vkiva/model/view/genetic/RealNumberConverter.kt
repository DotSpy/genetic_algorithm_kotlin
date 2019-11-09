package by.vkiva.model.view.genetic

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

object RealNumberConverter {

    fun convertToRealNumber(
        chromosome: Chromosome,
        lowerBorder: Long,
        highestBorder: Long
    ): Double =
        BigDecimal.valueOf(lowerBorder + (chromosome.getAsInt() * (highestBorder - lowerBorder) / (2.0.pow(chromosome.value.size.toDouble()) - 1)))
            .setScale(
                3,
                RoundingMode.HALF_EVEN
            ).toDouble()

}