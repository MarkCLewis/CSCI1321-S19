package section3.puyo

class Puyo(val x: Int, val y: Int, val color: PuyoColor.Value) extends Boba {
  def move(dx: Int, dy: Int): Puyo = {
    new Puyo(x + dx, y + dy, color)
  }
}