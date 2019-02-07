package section2.drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

object Main extends JFXApp {
  val boardWidth = 8 * Renderer.CellSize
  val boardHeight = 16 * Renderer.CellSize

  stage = new JFXApp.PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(boardWidth, boardHeight) {
      val canvas = new Canvas(boardWidth, boardHeight)
      val gc = canvas.graphicsContext2D
      val renderer = new Renderer(gc)
      val board = new Board
      content = canvas
      
      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => board.upPressed()
          case KeyCode.Down => board.downPressed()
          case KeyCode.Left => board.leftPressed()
          case KeyCode.Right => board.rightPressed()
          case _ =>
        }
      }
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => board.upReleased()
          case KeyCode.Down => board.downReleased()
          case KeyCode.Left => board.leftReleased()
          case KeyCode.Right => board.rightReleased()
          case _ =>
        }
      }

      var lastTime = -1L
      val timer = AnimationTimer(time => {
        if (lastTime != -1) {
          val delay = (time - lastTime) / 1e9
          board.update(delay)
          renderer.render(board)
        }
        lastTime = time
      })
      timer.start()

    }
  }
}