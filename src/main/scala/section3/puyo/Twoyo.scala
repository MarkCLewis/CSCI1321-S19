package section3.puyo

class Twoyo(val p1: Puyo, val p2: Puyo) {
  def move(dx: Int, dy: Int): Twoyo = {
    new Twoyo(p1.move(dx, dy), p2.move(dx, dy))
  }
}