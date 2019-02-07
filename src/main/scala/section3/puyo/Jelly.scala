package section3.puyo

class Jelly(val x: Int, val y: Int) extends Boba {
  def color: PuyoColor.Value = PuyoColor.Gray

  def move(dx: Int, dy: Int): Jelly = {
    new Jelly(x+dx, y + dy)
  }
}