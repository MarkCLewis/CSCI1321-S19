package section2.drmario

class Virus(val x: Int, val y: Int, val color: Int) extends Cell with BoardElement {
  def supported() = true
  def cells: List[Cell] = List(this)
}