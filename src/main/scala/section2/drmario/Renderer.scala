package section2.drmario

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  def render(board: Board): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, Main.boardWidth, Main.boardHeight)
  }
}