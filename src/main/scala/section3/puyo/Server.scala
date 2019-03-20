package section3.puyo

import java.net.ServerSocket
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.concurrent.LinkedBlockingQueue

object Server extends App {
  private var boards = List[Board]()
  private val boardQueue = new LinkedBlockingQueue[Board]

  val ss = new ServerSocket(4040)
  Future {
    while (true) {
      val sock = ss.accept()
      val in = new ObjectInputStream(sock.getInputStream)
      val out = new ObjectOutputStream(sock.getOutputStream)
      val board = new Board(sock, in, out)
      boardQueue.put(board)
    }
  }

  var lastTime = -1L
  while (true) {
    while(boardQueue.size() > 0) {
      boards ::= boardQueue.take()
    }
    val time = System.nanoTime()
    if (lastTime != -1) {
      val delay = (time - lastTime) / 1e9
      for (board <- boards) {
        board.update(delay)
        val pb = board.makePassable()
        // Send info to clients
      }
    }
    lastTime = time
  }

}