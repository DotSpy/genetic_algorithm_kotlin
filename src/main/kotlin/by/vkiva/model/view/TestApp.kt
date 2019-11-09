package by.vkiva.model.view

import javafx.collections.FXCollections
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import tornadofx.*

private const val WINDOW_HEIGHT = 600.0
private const val WINDOW_WIDTH = 1024.0

class MainApp : App(WorldView::class)

data class Actor(val x: Double, val y: Double)

class WorldView : View("Actor Simulator") {

    private val actors = FXCollections.observableArrayList<Actor>()
    private val chromosomeData = FXCollections.observableArrayList<XYChart.Data<Number, Number>>()
    private val chromosomeSeries = XYChart.Series<Number, Number>(chromosomeData).also {
        it.name = "Generation"
    }

//    override fun onDock() {
//        runAsync {
//            Plot.getPoints().forEach { (x, y) ->
//                runLater {
//                    chromosomeData.add(XYChart.Data(x, y))
//                }
//                Thread.sleep(100)
//            }
//        }
//    }

    override fun onBeforeShow() {
        this.primaryStage.minWidth = 1500.0
        this.primaryStage.minHeight = 800.0
    }

    override val root = form {
        scatterchart("Genetic Algorithm", NumberAxis(), NumberAxis()) {
            series("some siries", chromosomeData)
        }
        group {
            bindChildren(actors) {
                circle {
                    centerX = it.x
                    centerY = it.y
                    radius = 10.0
                    println("drew circle")
                }
            }
        }
        button("Add actor") {
            action {
                runAsync {
                    Plot.getPoints().forEach { (x, y) ->
                        runLater {
                            chromosomeData.add(XYChart.Data(x, y))
                        }
                        Thread.sleep(100)
                    }
                }
            }
        }
    }
}