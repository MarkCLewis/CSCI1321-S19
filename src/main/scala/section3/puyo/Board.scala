package section3.puyo

class Board {
  private var _bobas = List.tabulate[Boba](6)(i => new Jelly(i, 11))
  private var _current = new Twoyo(new Puyo(2, -1, PuyoColor.random()), 
      new Puyo(2, 0, PuyoColor.random()))
  private var _next = new Twoyo(new Puyo(6, 0, PuyoColor.random()), 
      new Puyo(6, 1, PuyoColor.random())) 
  
  def bobas = _bobas
  def current = _current
  def next = _next
  
  def update(delay: Double): Unit = {
    _current = current.fall()
  }
}