package section3.puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

// TODO - draw next piece
class Renderer(gc: GraphicsContext) {
  import Renderer._

  def render(board: PassableBoard): Unit = {
    gc.fill = Color.DarkGreen
    gc.fillRect(0, 0, Client.canvasWidth, Client.canvasHeight)

    for (boba <- board.bobas) {
      drawBoba(boba)
    }
    if (board.drawCurrent) {
      drawBoba(board.p1)
      drawBoba(board.p2)
    }
  }

  def drawBoba(boba: PassableBoba): Unit = {
    boba.color match {
      case PuyoColor.Red     => gc.fill = Color.Red
      case PuyoColor.Yellow  => gc.fill = Color.Yellow
      case PuyoColor.Blue    => gc.fill = Color.Blue
      case PuyoColor.Green   => gc.fill = Color.Green
      case PuyoColor.Magenta => gc.fill = Color.Magenta
      case PuyoColor.Gray    => gc.fill = Color.Gray
    }
    gc.fillOval(boba.x * CellSize, boba.y * CellSize, CellSize, CellSize)
  }
}

object Renderer {
  val CellSize = 30
}