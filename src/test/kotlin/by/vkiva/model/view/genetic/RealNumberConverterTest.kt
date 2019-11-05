package by.vkiva.model.view.genetic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RealNumberConverterTest {

    @Test
    fun testRealNumberFormula() {
        val chromosome = Chromosome(15)
        assertEquals(-10.0, RealNumberConverter.convertToRealNumber(chromosome, -10, 10))
    }

    @Test
    fun testHigherBorderFormula() {
        val chromosome = Chromosome(15)
        chromosome.value[0] = true
        chromosome.value[1] = true
        chromosome.value[2] = true
        chromosome.value[3] = true
        chromosome.value[4] = true
        chromosome.value[5] = true
        chromosome.value[6] = true
        chromosome.value[7] = true
        chromosome.value[8] = true
        chromosome.value[9] = true
        chromosome.value[10] = true
        chromosome.value[11] = true
        chromosome.value[12] = true
        chromosome.value[13] = true
        chromosome.value[14] = true
        assertEquals(10.0, RealNumberConverter.convertToRealNumber(chromosome, -10, 10))
    }
}