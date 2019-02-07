package section3.puyo

class Board {
  private var _bobas = List.tabulate[Boba](6)(i => new Jelly(i, 11))
  private var _current = new Twoyo(new Puyo(2, -1, PuyoColor.random()), 
      new Puyo(2, 0, PuyoColor.random()))
  private var _next = new Twoyo(new Puyo(6, 0, PuyoColor.random()), 
      new Puyo(6, 1, PuyoColor.random()))
  private var upHeld = false
  private var downHeld = false
  private var leftHeld = false
  private var rightHeld = false
  
  def bobas = _bobas
  def current = _current
  def next = _next
  
  private val fallInterval = 1.0
  private var fallDelay = 0.0
  
  def update(delay: Double): Unit = {
    fallDelay += delay
    if(leftHeld) _current = current.move(-1, 0) 
    if(rightHeld) _current = current.move(1, 0) 
    if (fallDelay > fallInterval) {
      _current = current.move(0, 1)
      fallDelay = 0.0
    }
  }
  
  def upPressed() = upHeld = true
  def downPressed() = downHeld = true
  def leftPressed() = leftHeld = true
  def rightPressed() = rightHeld = true
  def upReleased() = upHeld = false
  def downReleased() = downHeld = false
  def leftReleased() = leftHeld = false
  def rightReleased() = rightHeld = false
}