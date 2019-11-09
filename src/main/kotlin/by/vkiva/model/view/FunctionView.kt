package by.vkiva.model.view

import by.vkiva.model.view.controller.GeneticController
import by.vkiva.model.view.genetic.Chromosome
import by.vkiva.model.view.genetic.RealNumberConverter
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*


class FunctionView : View() {

    private val chromosomeData = FXCollections.observableArrayList<XYChart.Data<Number, Number>>()
    private val populationSize = SimpleIntegerProperty()
    private val generationCount = SimpleIntegerProperty()
    private val genCount = SimpleIntegerProperty()
    private val probabilityOfCrossing = SimpleDoubleProperty()
    private val probabilityOfMutation = SimpleDoubleProperty()
    private val middleFitnessValue = SimpleDoubleProperty()
    private val bestFitnessValue = SimpleDoubleProperty()

    override fun onBeforeShow() {
        this.primaryStage.minWidth = 1500.0
        this.primaryStage.minHeight = 800.0
    }

    override val root = form {
        fieldset {
            field("Population size") {
                textfield(populationSize).text = "5"
            }
            field("Generation count") {
                textfield(generationCount).text = "5"
            }
            field("Maximum dimension of individual solution (gen count)") {
                textfield(genCount).text = "15"
            }
            field("Probability of crossing") {
                textfield(probabilityOfCrossing).text = "0.9"
            }
            field("Probability of mutation") {
                textfield(probabilityOfMutation).text = "0.1"
            }
            scatterchart("Genetic Algorithm", NumberAxis(), NumberAxis()) {
                series("Genetic", chromosomeData)
                series("(0.7t-1.7)*cos(0.5pi*t+1.5) t[-5,5]") {
                    Plot.getPoints().forEach { (x, y) ->
                        data(x, y)
                    }
                }
            }
            button("Start") {
                action {
                    runAsync {
                        var generation = emptyList<Chromosome>()
                        val generationCount = generationCount.value
                        for (i in 1..generationCount) {
                            val uiData = getNextGeneration(generation)
                            generation = uiData.generation
                            runLater {
                                bestFitnessValue.value = uiData.bestFitnessValue
                                middleFitnessValue.value = uiData.middleFitnessValue
                                chromosomeData.clear()
                                uiData.generationPoints.forEach { (x, y) ->
                                    chromosomeData.add(XYChart.Data(x, y))
                                }
                            }
                            Thread.sleep(1500)
                        }
                    }
                }
            }
            hbox {
                label("Best Fitness Value = ")
                label(bestFitnessValue)
            }
            hbox {
                label("Middle Fitness Value = ")
                label(middleFitnessValue)
            }
        }
    }

    private fun getNextGeneration(generation: List<Chromosome>): UiData {
        val generationCount = generationCount.value
        val generationPoints = mutableListOf<Pair<Double, Double>>()
        val population = GeneticController.calculate(
            populationSize.value,
            genCount.value,
            probabilityOfCrossing.value,
            probabilityOfMutation.value,
            generationCount,
            generation
        )
        for (chromosome in population.population) {
            val realNumber = RealNumberConverter.convertToRealNumber(
                chromosome,
                Plot.T_MIN.toLong(),
                Plot.T_MAX.toLong()
            )
            generationPoints.add(Pair(realNumber, Plot.getYValueForX(realNumber)))
        }
        return UiData(
            generationPoints,
            population.bestFitnessValue,
            population.middleFitnessValue,
            population.population
        )
    }
}

data class UiData(
    val generationPoints: List<Pair<Double, Double>>,
    val bestFitnessValue: Double,
    val middleFitnessValue: Double,
    val generation: List<Chromosome>
)