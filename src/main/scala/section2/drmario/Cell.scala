package section2.drmario

trait Cell {
  def x: Int
  def y: Int
  def color: DrMarioColor.Value
  def style: Int

  def supported(): Boolean
  
  def makePassable(): PassableCell = PassableCell(x, y, color, style)
}