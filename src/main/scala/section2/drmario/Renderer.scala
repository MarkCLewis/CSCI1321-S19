package section2.drmario

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  import Renderer._
  
  def render(board: Board): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, Main.boardWidth, Main.boardHeight)
    
    for(be <- board.currentPill :: board.elements; cell <- be.cells) {
      cell.color match {
        case DrMarioColor.Red => gc.fill = Color.Red
        case DrMarioColor.Yellow => gc.fill = Color.Yellow
        case DrMarioColor.Blue => gc.fill = Color.Blue
      }
      cell match {
        case _: Virus =>
          gc.fillOval(cell.x * CellSize, cell.y * CellSize, CellSize, CellSize)
        case _: PillPiece =>
          gc.fillRect(cell.x * CellSize, cell.y * CellSize, CellSize, CellSize)
      }
    }
    
    // TODO - draw next pill
  }
}

object Renderer {
  val CellSize = 30
}