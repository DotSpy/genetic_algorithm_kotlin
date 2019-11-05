package by.vkiva.model.view

import by.vkiva.model.view.controller.GeneticController
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*

class FunctionView : View() {

    private val series = FXCollections.observableArrayList<XYChart.Series<Number, Number>>()
    private val populationSize = SimpleIntegerProperty()
    private val generationNumber = SimpleIntegerProperty()
    private val probabilityOfCrossing = SimpleDoubleProperty()
    private val probabilityOfMutation = SimpleDoubleProperty()

    override fun onBeforeShow() {
        this.primaryStage.minWidth = 1500.0
        this.primaryStage.minHeight = 800.0
    }

    override val root = form {
        fieldset {
            field("Population size") {
                textfield(populationSize)
            }
            field("Generation number") {
                textfield(generationNumber)
            }
            field("Probability of crossing") {
                textfield(probabilityOfCrossing)
            }
            field("Probability of mutation") {
                textfield(probabilityOfMutation)
            }
            val linechart = linechart("Genetic Algorithm", NumberAxis(), NumberAxis()) {
                series("(0.7t-1.7)*cos(0.5pi*t+1.5) t[-5,5]") {
                    Plot.getPoints().forEach { (x, y) ->
                        data(x, y)
                    }
                }
            }
            button("Start") {
                action {
                    //                    bindChildren(series) {
////                        it.name = ("(0.7t-1.7)*cos(0.5pi*t+1.5) t[-5,5]") {
////                            Plot.getPoints().forEach { (x, y) ->
////                                data(x, y)
////                            }
////                        }
//                    }
                    linechart.series("Generation") {
                        GeneticController.calculate(
                            populationSize.value,
                            15,
                            probabilityOfCrossing.value,
                            probabilityOfMutation.value,
                            generationNumber.value,
                            emptyList()
                        )
                    }
                }
            }
        }
    }
}
