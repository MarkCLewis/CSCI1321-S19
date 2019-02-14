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
      if (leftHeld) _currentPill = currentPill.move(-1, 0, isClear)
      if (rightHeld) _currentPill = currentPill.move(1, 0, isClear)
      if (downHeld) dropPill()
      if (upHeld) _currentPill = currentPill.rotate()
      moveDelay = 0.0
    }
    if (fallDelay >= fallInterval) {
      dropPill()
      fallDelay = 0.0
    }
  }

  def dropPill(): Unit = {
    if (currentPill.canMove(0, 1, isClear)) {
      _currentPill = currentPill.move(0, 1, isClear)
    } else {
      _elements ::= _currentPill
      clearMatches()
      _currentPill = nextPill
      _nextPill = new Pill(List(
        new PillPiece(3, 0, DrMarioColor.random()),
        new PillPiece(4, 0, DrMarioColor.random())))
    }
  }

  def isClear(x: Int, y: Int): Boolean = {
    elements.forall(e => e.cells.forall(c => c.x != x || c.y != y))
  }
  
  def clearMatches(): Boolean = {
    // Find vertical
    for(x <- 0 until Board.width) {
      for(y <- 0 until Board.height) {
        
      }
    }
    false
  }
  
  def cellAt(x: Int, y: Int): Option[Cell] = {
    val elem = elements.find(e => e.cells.find(c => c.x == x && c.y == y).nonEmpty)
    ???
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