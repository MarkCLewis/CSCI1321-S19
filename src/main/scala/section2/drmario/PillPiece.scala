package section2.drmario

class PillPiece(val x: Int, val y: Int, val color: DrMarioColor.Value) extends Cell {
  def supported(): Boolean = ???
  def fall(): PillPiece = new PillPiece(x, y+1, color)
}