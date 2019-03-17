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
  
  def rotate(isClear: (Int, Int) => Boolean): Pill = {
    if (cells(0).y == cells(1).y) { // Horizontal
      val sortCells = cells.sortBy(_.x)
      val newPos = List(sortCells(0), sortCells(1).move(-1, -1))
      if(newPos.forall(p => isClear(p.x, p.y))) new Pill(newPos) else this 
    } else {
      val sortCells = cells.sortBy(_.y)
      val newPos = List(sortCells(0).move(0, 1), sortCells(1).move(1, 0))
      if(newPos.forall(p => isClear(p.x, p.y))) new Pill(newPos) else this 
    }
  }
  
  def removeCells(toRemove: collection.Set[Cell]): Option[BoardElement] = {
    val cellsLeft = cells.filter(c => !toRemove(c))
    if(cellsLeft.isEmpty) None else Some(new Pill(cellsLeft))
  }
  
  def supported(grid: Array[Array[Option[(BoardElement, Cell)]]]): Boolean = {
    cells.exists(c => c.y == Board.Height-1 || grid(c.x)(c.y+1).nonEmpty)
  }
}