package section3.puyo

class Jelly(val x: Int, val y: Int) extends Boba {
  def color: PuyoColor.Value = PuyoColor.Gray

  def fall(): Jelly = {
    new Jelly(x, y + 1)
  }
}