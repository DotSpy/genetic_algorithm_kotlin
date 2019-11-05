package by.vkiva.model.view

import kotlin.math.cos

object Plot {

    const val T_MIN = -5.0
    const val T_MAX = 5.0
    private const val TICK = 0.001

    private val points: Map<Double, Double>
    private val max: Double

    init {
        val points = mutableMapOf<Double, Double>()
        var currentStep = T_MIN
        var max: Double = Double.MIN_VALUE
        while (currentStep <= T_MAX) {
            val currentValue = (0.7 * currentStep - 1.7) * cos(0.5 * Math.PI * currentStep + 1.5)
            points[currentStep] = currentValue
            if (currentValue > max) {
                max = currentValue
            }
            currentStep += TICK
        }
        this.points = points
        this.max = max
    }

    fun getPoints() = points

    fun getValueForX(x: Double) = points.getValue(x)

}