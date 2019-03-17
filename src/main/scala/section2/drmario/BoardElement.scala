package section2.drmario

trait BoardElement {
  def cells: List[Cell]
  
  def move(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): BoardElement
  def removeCells(toRemove: collection.Set[Cell]): Option[BoardElement]
  def supported(grid: Array[Array[Option[(BoardElement, Cell)]]]): Boolean
}