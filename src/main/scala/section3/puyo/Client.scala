package section3.puyo

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import java.net.Socket
import scalafx.scene.control.TextInputDialog
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object Client extends JFXApp {
  val textDialog = new TextInputDialog("localhost")
  val machine = textDialog.showAndWait().getOrElse("localhost")
  val sock = new Socket(machine, 4040)
  val in = new ObjectInputStream(sock.getInputStream)
  val out = new ObjectOutputStream(sock.getOutputStream)
  
  val canvasWidth = Board.Width * Renderer.CellSize
  val canvasHeight = Board.Height * Renderer.CellSize
  stage = new JFXApp.PrimaryStage {
    title = "Puyo"
    scene = new Scene(canvasWidth, canvasHeight) {
      val canvas = new Canvas(canvasWidth, canvasHeight)
      val gc = canvas.graphicsContext2D
      val renderer = new Renderer(gc)

      content = canvas
      
      onKeyPressed = (ke: KeyEvent) => {
        out.writeObject(KeyPressed(ke.code))
      }
      onKeyReleased = (ke: KeyEvent) => {
        out.writeObject(KeyReleased(ke.code))
      }
      
      
//            renderer.render(pb)
    }
  }
}