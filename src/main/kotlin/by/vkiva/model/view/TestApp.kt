package by.vkiva.model.view

import javafx.collections.FXCollections
import tornadofx.*
import java.util.concurrent.ThreadLocalRandom

private const val WINDOW_HEIGHT = 600.0
private const val WINDOW_WIDTH = 1024.0

class MainApp : App(WorldView::class)

data class Actor(val x: Double, val y: Double)

class WorldView : View("Actor Simulator") {

    private val actors = FXCollections.observableArrayList<Actor>()

    override fun onDock() {
        runAsync {
            repeat((0..100).count()) {
                val x = ThreadLocalRandom.current().nextDouble(0.0, WINDOW_WIDTH.toDouble())
                val y = ThreadLocalRandom.current().nextDouble(0.0, WINDOW_HEIGHT.toDouble())
                runLater {
                    actors.add(Actor(x, y))
                }
                Thread.sleep(100)
            }
        }
    }

    override val root = stackpane {
        setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT)
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
                actors.add(Actor(0.0, 0.0))
            }
        }
    }
}