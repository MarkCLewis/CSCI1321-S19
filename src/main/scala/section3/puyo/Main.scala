package section3.puyo

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas

object Main extends JFXApp {
  val canvasWidth = 6*30
  val canvasHeight = 12*30
  stage = new JFXApp.PrimaryStage {
    title = "Puyo"
    scene = new Scene(canvasWidth, canvasHeight) {
      val canvas = new Canvas(canvasWidth, canvasHeight)
      val gc = canvas.graphicsContext2D
      val renderer = new Renderer(gc)
      val board = new Board
      
      content = canvas
      
      renderer.render(board)
    }
  }
}