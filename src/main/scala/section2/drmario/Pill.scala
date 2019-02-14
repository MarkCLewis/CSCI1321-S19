package section2.drmario

class Pill(val cells: List[PillPiece]) extends BoardElement {
  def move(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Pill = {
    if (canMove(dx, dy, isClear)) {
      new Pill(cells.map(pp => pp.move(dx, dy)))
    } else this
  }
  
  def canMove(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Boolean = {
    cells.forall(_.canMove(dx, dy, isClear))
  }
  
  def rotate(): Pill = {
    if (cells(0).y == cells(1).y) { // Horizontal
      val sortCells = cells.sortBy(_.x)
      new Pill(List(sortCells(0), sortCells(1).move(-1, -1))) 
    } else {
      val sortCells = cells.sortBy(_.y)
      new Pill(List(sortCells(0).move(0, 1), sortCells(1).move(1, 0))) 
    }
  }
}