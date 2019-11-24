package by.vkiva.model.view.genetic

import by.vkiva.model.view.Plot

data class Chromosome(
    val size: Int,
    var decimalValue: Double? = null
) {

    constructor(chromosome: List<Boolean>) : this(chromosome.size) {
        value = chromosome.toBooleanArray()
        decimalValue = RealNumberConverter.convertToRealNumber(this, Plot.T_MIN.toLong(), Plot.T_MAX.toLong())
    }

    constructor(chromosome: BooleanArray) : this(chromosome.size) {
        value = chromosome
        decimalValue = RealNumberConverter.convertToRealNumber(this, Plot.T_MIN.toLong(), Plot.T_MAX.toLong())
    }

    var value: BooleanArray = BooleanArray(size) { false }
        set(newValue) {
            field = newValue
            decimalValue = RealNumberConverter.convertToRealNumber(this, Plot.T_MIN.toLong(), Plot.T_MAX.toLong())
        }

    fun getAsInt(): Int {
        var n = 0
        for (b in value.lastIndex downTo 0)
            n = n shl 1 or if (value[b]) 1 else 0
        return n
    }
}