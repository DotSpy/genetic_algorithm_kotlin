package by.vkiva.model.view.genetic

data class Chromosome(
    val size: Int
) {

    constructor(chromosome: List<Boolean>) : this(chromosome.size) {
        value = chromosome.toBooleanArray()
    }

    var value: BooleanArray = BooleanArray(size) { false }

    fun getAsInt(): Int {
        var n = 0
        for (b in value.lastIndex downTo 0)
            n = n shl 1 or if (value[b]) 1 else 0
        return n
    }
}