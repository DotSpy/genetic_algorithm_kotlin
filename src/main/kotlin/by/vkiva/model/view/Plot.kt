package by.vkiva.model.view

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.cos

object Plot {

    const val T_MIN = -5.0
    const val T_MAX = 5.0
    private const val TICK = 0.001

    private val points: Map<Double, Double>
    val max: Double
    val min: Double

    init {
        val points = mutableMapOf<Double, Double>()
        var currentStep = T_MIN
        var max: Double = Double.MIN_VALUE
        var min = Double.MAX_VALUE
        while (currentStep <= T_MAX) {
            val currentValue = (0.7 * currentStep - 1.7) * cos(0.5 * Math.PI * currentStep + 1.5)
            points[currentStep] = currentValue
            if (currentValue > max) {
                max = currentValue
            }
            if (currentValue < min) {
                min = currentValue
            }
            currentStep = BigDecimal.valueOf(TICK + currentStep).setScale(3, RoundingMode.HALF_EVEN).toDouble()
        }
        this.points = points
        this.max = max
        this.min = min
    }

    fun getPoints() = points

    fun getYValueForX(x: Double) = points.getValue(x)

}