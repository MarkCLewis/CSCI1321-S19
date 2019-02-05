package section3.puyo

class Twoyo(val p1: Puyo, val p2: Puyo) {
  def fall(): Twoyo = {
    new Twoyo(p1.fall, p2.fall)
  }
}