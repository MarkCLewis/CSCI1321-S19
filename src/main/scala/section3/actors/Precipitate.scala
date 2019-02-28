package section3.actors

import akka.actor.Actor

class Precipitate(width: Int, height: Int) extends Actor {
  import Precipitate._
  
  /**
   * Receive required by all actors. Has cases for all the messages that this actor should get.
   */
  def receive = {
    case MoveFrom(x, y) =>
      val (nx, ny) = calcNew(x, y)
      sender ! Solution.CheckLocation(nx, ny, x, y)
    case m => println("Unhandled message in Precipitate: " + m)
  }
  
  /**
   * Calculate a new point using recursion instead of a loop because I don't like vars. Checking the outer bounds
   * and making sure we move puts a little more work here and not in the Solution.
   */
  def calcNew(x: Int, y: Int): (Int, Int) = {
    val nx = x + util.Random.nextInt(3)-1
    val ny = y + util.Random.nextInt(2) // Don't allow moving up to mke things run faster.
    if ((nx < 0 || nx >= width || ny < 0 || ny >= height) || (nx == x && ny == y)) calcNew(x, y) else (nx, ny)
  }
}

object Precipitate {
  case class MoveFrom(x: Int, y: Int)
}