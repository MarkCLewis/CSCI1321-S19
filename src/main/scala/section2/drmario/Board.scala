package section2.drmario

class Board {
  private var _elements = List.tabulate[BoardElement](10)(i =>
    new Virus(util.Random.nextInt(8), util.Random.nextInt(16), DrMarioColor.random()))
  private var _currentPill = new Pill(List(
    new PillPiece(3, 0, DrMarioColor.random()),
    new PillPiece(4, 0, DrMarioColor.random())))
  private var _nextPill = new Pill(List(
    new PillPiece(3, 0, DrMarioColor.random()),
    new PillPiece(4, 0, DrMarioColor.random())))
  private val fallInterval = 1.0
  private var fallDelay = 0.0
  private val moveInterval = 0.1
  private var moveDelay = 0.0
  private var upHeld = false
  private var leftHeld = false
  private var rightHeld = false
  private var downHeld = false

  def elements = _elements
  def currentPill = _currentPill
  def nextPill = _nextPill

  def update(delay: Double): Unit = {
    fallDelay += delay
    moveDelay += delay
    if (moveDelay >= moveInterval) {
      if (leftHeld) _currentPill = currentPill.move(-1, 0)
      if (rightHeld) _currentPill = currentPill.move(1, 0)
      moveDelay = 0.0
    }
    if (fallDelay >= fallInterval) {
      if (currentPill.canMove(0, 1)) {
        _currentPill = currentPill.move(0, 1)
      } else {
        _elements ::= _currentPill
        _currentPill = nextPill
        _nextPill = new Pill(List(
          new PillPiece(3, 0, DrMarioColor.random()),
          new PillPiece(4, 0, DrMarioColor.random())))
      }
      fallDelay = 0.0
    }
  }

  def upPressed() = upHeld = true
  def leftPressed() = leftHeld = true
  def rightPressed() = rightHeld = true
  def downPressed() = downHeld = true
  def upReleased() = upHeld = false
  def leftReleased() = leftHeld = false
  def rightReleased() = rightHeld = false
  def downReleased() = downHeld = false
}

object Board {
  val width = 8
  val height = 16
}