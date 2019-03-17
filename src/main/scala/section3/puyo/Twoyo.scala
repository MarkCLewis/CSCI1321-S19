package section3.puyo

class Twoyo(val p1: Puyo, val p2: Puyo) {
  def move(dx: Int, dy: Int, boardClear: (Int, Int) => Boolean): Twoyo = {
    if (isClear(dx, dy, boardClear)) {
      new Twoyo(p1.move(dx, dy), p2.move(dx, dy))
    } else this
  }

  def isClear(dx: Int, dy: Int, boardClear: (Int, Int) => Boolean): Boolean = {
    p1.isClear(dx, dy, boardClear) && p2.isClear(dx, dy, boardClear)
  }

  def rotate(boardClear: (Int, Int) => Boolean): Twoyo = {
    if (p1.y == p2.y) { // Horizontal
      val sorted = Array(p1, p2).sortBy(_.x)
      val newPos = Array(sorted(0), sorted(1).move(-1, -1))
      if (newPos.forall(p => boardClear(p.x, p.y))) new Twoyo(newPos(0), newPos(1)) else this
    } else {
      val sorted = Array(p1, p2).sortBy(_.y)
      val newPos = Array(sorted(0).move(0, 1), sorted(1).move(1, 0))
      if (newPos.forall(p => boardClear(p.x, p.y))) new Twoyo(newPos(0), newPos(1)) else this
    }
  }
}