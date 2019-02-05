package section3.puyo

trait Boba {
  def x: Int
  def y: Int
  def color: PuyoColor.Value
  
  def fall(): Boba
}