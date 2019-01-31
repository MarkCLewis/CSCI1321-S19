package section3.puyo

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  def render(board: Board): Unit = {
    gc.fill = Color.DarkGreen
    gc.fillRect(0, 0, Main.canvasWidth, Main.canvasHeight)
  }
}