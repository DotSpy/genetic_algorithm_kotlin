package by.vkiva.model.view.genetic

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger


internal class ChromosomeTest {

    @Test
    fun convertBitSetTest() {
        val chromosome = Chromosome(3)
        chromosome.value[2] = true
        Assertions.assertEquals(4, chromosome.getAsInt())
    }

    @Test
    fun double() {
        val double1 = 5.12
        val double2 = -5.12
        val binaryString1 = java.lang.Long.toBinaryString(double1.toBits())
        val binaryString2 = java.lang.Long.toBinaryString(double2.toBits())
        println(binaryString1)
        println(binaryString2)
        val binaryList = mutableListOf<Boolean>()
        for (char in binaryString1) {
            if (char == '1') {
                binaryList.add(true)
            } else {
                binaryList.add(false)
            }
        }

        val doubleBits1 = Double.fromBits(parseLong(binaryString1, 2))
        val doubleBits2 = Double.fromBits(parseLong(binaryString2, 2))
        println(doubleBits1)
        println(doubleBits2)
    }

    private fun parseLong(s: String, base: Int): Long {
        return BigInteger(s, base).toLong()
    }
}