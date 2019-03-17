package section2.drmario

class Virus(val x: Int, val y: Int, val color: DrMarioColor.Value) extends Cell with BoardElement {
  def supported() = true
  def cells: List[Cell] = List(this)
  
  def move(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Virus = this
  
  def removeCells(toRemove: collection.Set[Cell]): Option[BoardElement] = {
    if(toRemove(this)) None else Some(this)
  }
  
  def supported(grid: Array[Array[Option[(BoardElement, Cell)]]]): Boolean = true
}