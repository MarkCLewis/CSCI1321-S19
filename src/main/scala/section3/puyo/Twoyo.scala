package section3.puyo

class Twoyo(val p1: Puyo, val p2: Puyo) {
  def move(dx: Int, dy: Int, boardClear: (Int, Int) => Boolean): Twoyo = {
    if(isClear(dx, dy, boardClear)) {
      new Twoyo(p1.move(dx, dy), p2.move(dx, dy))
    } else this
  }
  
  def isClear(dx: Int, dy: Int, boardClear: (Int, Int) => Boolean): Boolean = {
    p1.isClear(dx, dy, boardClear) && p2.isClear(dx, dy, boardClear)
  }
}