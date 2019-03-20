package section3.puyo

trait Boba {
  def x: Int
  def y: Int
  def color: PuyoColor.Value
  
  def move(dx: Int, dy: Int): Boba
  
  def makePassable(): PassableBoba = PassableBoba(x, y, color)
}