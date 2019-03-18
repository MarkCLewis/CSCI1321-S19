package section2.drmario

import collection.mutable

class Board {
  private var _elements = List.tabulate[BoardElement](10)(i =>
    new Virus(util.Random.nextInt(Board.Width), util.Random.nextInt(Board.Height - 3) + 3, DrMarioColor.random()))
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
  private var checkSupport = false

  def elements = _elements
  def currentPill = _currentPill
  def nextPill = _nextPill
  
  def drawCurrent = !checkSupport

  def update(delay: Double): Unit = {
    moveDelay += delay
    if (checkSupport) {
      if (moveDelay > moveInterval) {
        val fastGrid = Array.fill(Board.Width, Board.Height)(None: Option[(BoardElement, Cell)])
        for (e <- elements; c <- e.cells; if c.y >= 0) fastGrid(c.x)(c.y) = Some(e -> c)
        if (!dropUnsupported(fastGrid)) {
          if (!checkRemoveByColor(fastGrid)) {
            checkSupport = false
          }
          _currentPill = nextPill
          _nextPill = new Pill(List(
            new PillPiece(3, 0, DrMarioColor.random()),
            new PillPiece(4, 0, DrMarioColor.random())))
        }
        moveDelay = 0.0
      }
    } else {
      fallDelay += delay
      if (moveDelay >= moveInterval) {
        if (leftHeld) _currentPill = currentPill.move(-1, 0, isClear)
        if (rightHeld) _currentPill = currentPill.move(1, 0, isClear)
        if (downHeld) dropPill()
        if (upHeld) _currentPill = currentPill.rotate(isClear)
        moveDelay = 0.0
      }
      if (fallDelay >= fallInterval) {
        dropPill()
        fallDelay = 0.0
      }
    }
  }

  def dropPill(): Unit = {
    if (currentPill.canMove(0, 1, isClear)) {
      _currentPill = currentPill.move(0, 1, isClear)
    } else {
      _elements ::= _currentPill
      checkSupport = true
    }
  }

  def isClear(x: Int, y: Int): Boolean = {
    x >= 0 && x < Board.Width && y < Board.Height && elements.forall(e => e.cells.forall(c => c.x != x || c.y != y))
  }
  
  /**
   * Go through the board from the bottom to the top and if an boba isn't supported, drop it.
   * @param grid This is a 2D array of cell options. It is used to make this run faster.
   */
  private def dropUnsupported(grid: Array[Array[Option[(BoardElement, Cell)]]]): Boolean = {
    var ret = false
    val alreadyMoved = mutable.Set[BoardElement]()
    _elements = (for (y <- Board.Height - 1 to 0 by -1; x <- 0 until Board.Width; (be, c) <- grid(x)(y); if !alreadyMoved(be)) yield {
      if (y < Board.Height - 1 && !be.supported(grid)) {
        ret = true
        val newElement = be.move(0, 1, (x, y) => true)
        for (oldCell <- be.cells) grid(oldCell.x)(oldCell.y) = None
        for (newCell <- newElement.cells) grid(newCell.x)(newCell.y) = Some(newElement -> newCell)
        alreadyMoved += newElement
        newElement
      } else be
    }).toList
    ret
  }

  /**
   * Go through the board and find groups of Bobas with four of the same color touching to remove them.
   * @param grid This is a 2D array of cell options. It is used to make this run faster.
   */
  private def checkRemoveByColor(grid: Array[Array[Option[(BoardElement, Cell)]]]): Boolean = {
    val toRemove = mutable.Set[Cell]()
    // Check vertical stacks
    for(x <- 0 until Board.Width; y <- 0 until Board.Height-3; (_, c) <- grid(x)(y)) {
      val offsets = (y+1 until Board.Height).takeWhile(ny => grid(x)(ny).map(_._2.color == c.color).getOrElse(false))
      if(offsets.length >= 3) {  // We have 4 in a row
        toRemove += c
        toRemove ++= offsets.map(ny => grid(x)(ny).get._2) 
      }
    }
    // Check horizontal rows
    for(x <- 0 until Board.Width-3; y <- 0 until Board.Height; (_, c) <- grid(x)(y)) {
      val offsets = (x+1 until Board.Width).takeWhile(nx => grid(nx)(y).map(_._2.color == c.color).getOrElse(false))
      if(offsets.length >= 3) {  // We have 4 in a row
        toRemove += c
        toRemove ++= offsets.map(nx => grid(nx)(y).get._2) 
      }
    }
    _elements = elements.flatMap(e => e.removeCells(toRemove))
    toRemove.nonEmpty
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
  val Width = 8
  val Height = 16
}