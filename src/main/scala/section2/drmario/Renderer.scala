package section2.drmario

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  import Renderer._
  
  def render(board: PassableBoard): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, Main.boardWidth, Main.boardHeight)
    
    for(cell <- (if (board.drawCurrent) List(board.pp1, board.pp2) else Nil) ++: board.cells) {
      cell.color match {
        case DrMarioColor.Red => gc.fill = Color.Red
        case DrMarioColor.Yellow => gc.fill = Color.Yellow
        case DrMarioColor.Blue => gc.fill = Color.Blue
      }
      cell.style match {
        case 0 =>
          gc.fillOval(cell.x * CellSize, cell.y * CellSize, CellSize, CellSize)
        case 1 =>
          gc.fillRect(cell.x * CellSize, cell.y * CellSize, CellSize, CellSize)
      }
    }
    
    // TODO - draw next pill
  }
}

object Renderer {
  val CellSize = 30
}