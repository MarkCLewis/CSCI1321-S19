package section3.puyo

import collection.mutable
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import scalafx.scene.input.KeyCode

class Board(sock: Socket, in: ObjectInputStream, out: ObjectOutputStream) {
  private var _bobas = List.tabulate[Boba](6)(i => new Jelly(i, 11))
  private var _current = new Twoyo(
    new Puyo(2, -1, PuyoColor.random()),
    new Puyo(2, 0, PuyoColor.random()))
  private var _next = new Twoyo(
    new Puyo(2, -1, PuyoColor.random()),
    new Puyo(2, 0, PuyoColor.random()))
  private var upHeld = false
  private var downHeld = false
  private var leftHeld = false
  private var rightHeld = false
  private var checkSupport = false

  def bobas = _bobas
  def current = _current
  def next = _next

  private val fallInterval = 1.0
  private var fallDelay = 0.0
  private val moveInterval = 0.1
  private var moveDelay = 0.0
  private val offsets = List((-1, 0), (1, 0), (0, -1), (0, 1))

  def drawCurrent = !checkSupport

  def checkInput(): Unit = {
    in.readObject() match {
      case KeyPressed(code) =>
        code match {
          case KeyCode.Up => upPressed()
          case KeyCode.Down => downPressed()
          case KeyCode.Left => leftPressed()
          case KeyCode.Right => rightPressed()
          case _ =>
        }
      case KeyReleased(code) =>
        code match {
          case KeyCode.Up => upReleased()
          case KeyCode.Down => downReleased()
          case KeyCode.Left => leftReleased()
          case KeyCode.Right => rightReleased()
          case _ =>
        }
    }
  }

  def sendBoardUpdate(pb: PassableBoard): Unit = {
    out.writeObject(pb)
  }

  def update(delay: Double): Boolean = {
    moveDelay += delay
    if (checkSupport) {
      if (moveDelay > moveInterval) {
        val fastGrid = Array.fill(Board.Width, Board.Height)(None: Option[Boba])
        for (b <- bobas; if b.y >= 0) fastGrid(b.x)(b.y) = Some(b)
        if (!dropUnsupported(fastGrid)) {
          if (!checkRemoveByColor(fastGrid)) {
            checkSupport = false
          }
          _current = next
          _next = new Twoyo(
            new Puyo(2, -1, PuyoColor.random()),
            new Puyo(2, 0, PuyoColor.random()))
        }
        moveDelay = 0.0
        true
      } else false
    } else {
      var somethingMoved = false
      fallDelay += delay
      if (moveDelay > moveInterval) {
        if (leftHeld) _current = current.move(-1, 0, boardClear)
        if (rightHeld) _current = current.move(1, 0, boardClear)
        if (downHeld) dropTwoyo()
        if (upHeld) _current = current.rotate(boardClear)
        moveDelay = 0.0
        somethingMoved = true
      }
      if (fallDelay > fallInterval) {
        dropTwoyo()
        fallDelay = 0.0
        somethingMoved = true
      }
      somethingMoved
    }
  }

  def dropTwoyo(): Unit = {
    if (current.isClear(0, 1, boardClear)) {
      _current = current.move(0, 1, boardClear)
    } else {
      _bobas ::= current.p1
      _bobas ::= current.p2
      checkSupport = true
    }
  }

  def makePassable(): PassableBoard = {
    PassableBoard(bobas.map(_.makePassable()), drawCurrent,
      current.p1.makePassable(), current.p2.makePassable())
  }

  def boardClear(x: Int, y: Int): Boolean = {
    x >= 0 && x < Board.Width && y < Board.Height && bobas.forall(b => b.x != x || b.y != y)
  }

  /**
   * Go through the board from the bottom to the top and if an boba isn't supported, drop it.
   * @param grid This is a 2D array of boba options. It is used to make this run faster.
   */
  private def dropUnsupported(grid: Array[Array[Option[Boba]]]): Boolean = {
    var ret = false
    _bobas = (for (y <- Board.Height - 1 to 0 by -1; x <- 0 until Board.Width; b <- grid(x)(y)) yield {
      if (y < Board.Height - 1 && grid(x)(y + 1).isEmpty) {
        ret = true
        val newBoba = b.move(0, 1)
        grid(x)(y) = None
        grid(x)(y + 1) = Some(newBoba)
        newBoba
      } else b
    }).toList
    ret
  }

  /**
   * Go through the board and find groups of Bobas with four of the same color touching to remove them.
   * @param grid This is a 2D array of boba options. It is used to make this run faster.
   */
  private def checkRemoveByColor(grid: Array[Array[Option[Boba]]]): Boolean = {
    def getConnected(x: Int, y: Int, color: PuyoColor.Value, connected: mutable.Set[Boba]): Unit = {
      for {
        (ox, oy) <- offsets
        nx = x + ox; ny = y + oy
        if nx >= 0 && nx < Board.Width && ny >= 0 && ny < Board.Height
        b <- grid(nx)(ny)
        if !connected(b) && b.color == color
      } {
        connected += b
        getConnected(nx, ny, color, connected)
      }
    }
    val toRemove = mutable.Set[Boba]()
    for (b <- bobas; if !toRemove(b) && b.color != PuyoColor.Gray) {
      val connected = mutable.Set[Boba](b)
      getConnected(b.x, b.y, b.color, connected)
      if (connected.size >= 4) {
        toRemove ++= connected
        for {
          b <- connected
          (ox, oy) <- offsets
          nx = b.x + ox; ny = b.y + oy
          if nx >= 0 && nx < Board.Width && ny >= 0 && ny < Board.Height
          b2 <- grid(nx)(ny)
          if b2.color == PuyoColor.Gray
        } {
          toRemove += b2
        }
      }
    }
    _bobas = bobas.filter(b => !toRemove(b))
    toRemove.nonEmpty
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

object Board {
  val Width = 6
  val Height = 12
}
