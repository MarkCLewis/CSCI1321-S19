package section2.drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import java.net.Socket
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object Client extends JFXApp {
  val boardWidth = 8 * Renderer.CellSize
  val boardHeight = 16 * Renderer.CellSize

  val sock = new Socket("localhost", 4040)
  val out = new ObjectOutputStream(sock.getOutputStream)
  val in = new ObjectInputStream(sock.getInputStream)

  val canvas = new Canvas(boardWidth, boardHeight)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)
  
  stage = new JFXApp.PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(boardWidth, boardHeight) {
      content = canvas

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => out.writeObject(UpPressed)
          case KeyCode.Down => out.writeObject(DownPressed)
          case KeyCode.Left => out.writeObject(LeftPressed)
          case KeyCode.Right => out.writeObject(RightPressed)
          case _ =>
        }
      }
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => out.writeObject(UpReleased)
          case KeyCode.Down => out.writeObject(DownReleased)
          case KeyCode.Left => out.writeObject(LeftReleased)
          case KeyCode.Right => out.writeObject(RightReleased)
          case _ =>
        }
      }

      val timer = AnimationTimer { time =>
        if(in.available() > 0) {
          in.readObject() match {
            case pb:PassableBoard =>
              renderer.render(pb)
          }
        }
      }
    }
  }

}