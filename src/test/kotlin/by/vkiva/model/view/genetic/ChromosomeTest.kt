package by.vkiva.model.view.genetic

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ChromosomeTest {

    @Test
    fun convertBitSetTest() {
        val chromosome = Chromosome(3)
        chromosome.value[2] = true
        Assertions.assertEquals(4, chromosome.getAsInt())
    }
}