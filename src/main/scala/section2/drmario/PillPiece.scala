package section2.drmario

class PillPiece(val x: Int, val y: Int, val color: DrMarioColor.Value) extends Cell {
  def supported(): Boolean = ???
  def move(dx: Int, dy: Int): PillPiece = new PillPiece(x + dx, y + dy, color)
  def canMove(dx: Int, dy: Int, isClear: (Int, Int) => Boolean): Boolean = {
    x + dx >= 0 && x + dx < Board.Width && y + dy < Board.Height && isClear(x+dx, y+dy)
  }
}