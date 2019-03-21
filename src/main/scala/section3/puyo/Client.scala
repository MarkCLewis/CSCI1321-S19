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
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.application.Platform

object Client extends JFXApp {
  val textDialog = new TextInputDialog("localhost")
  val machine = textDialog.showAndWait().getOrElse("localhost")
  val sock = new Socket(machine, 4040)
  val out = new ObjectOutputStream(sock.getOutputStream)
  val in = new ObjectInputStream(sock.getInputStream)

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

      Future {
        while (true) {
          in.readObject() match {
            case pb: PassableBoard =>
              Platform.runLater(renderer.render(pb))
          }
        }
      }
    }
  }
}