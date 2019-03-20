package section2.drmario

import java.net.ServerSocket
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

case class Player(board: Board, sock: Socket, in: ObjectInputStream, out: ObjectOutputStream)

object Server extends App {
  private var players = List[Player]()

  private val ss = new ServerSocket(4040)
  Future {
    while (true) {
      val sock = ss.accept()
      val in = new ObjectInputStream(sock.getInputStream)
      val out = new ObjectOutputStream(sock.getOutputStream)
      val board = new Board
      val player = Player(board, sock, in, out)
      // TODO: This is probably a race condition. Fix if it causes problems.
      players ::= player
    }
  }

  var lastTime = -1L
  while (true) {
    val time = System.nanoTime()
    if (lastTime != -1) {
      val delay = (time - lastTime) / 1e9
      for (player <- players) {
        player.board.update(delay)
        val pb = player.board.makePassable()
        // ???
      }
    }
    lastTime = time
  }
}